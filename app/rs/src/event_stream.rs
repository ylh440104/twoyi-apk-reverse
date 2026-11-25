// This Source Code Form is subject to the terms of the Mozilla Public
// License, v. 2.0. If a copy of the MPL was not distributed with this
// file, You can obtain one at https://mozilla.org/MPL/2.0/.

use std::io;
use std::sync::mpsc::Receiver;

/// Stop when the producer is replaced or the guest no longer accepts events.
pub fn forward_events<T>(events: Receiver<T>, mut write: impl FnMut(T) -> io::Result<()>) {
    while let Ok(event) = events.recv() {
        if write(event).is_err() {
            break;
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;
    use std::sync::mpsc::channel;
    use std::thread;
    use std::time::Duration;

    #[test]
    fn producer_disconnect_drains_then_exits() {
        let (tx, rx) = channel();
        let (done, result) = channel();
        tx.send(1).unwrap();
        tx.send(2).unwrap();
        drop(tx);
        thread::spawn(move || {
            let mut received = Vec::new();
            forward_events(rx, |event| { received.push(event); Ok(()) });
            done.send(received).unwrap();
        });
        assert_eq!(result.recv_timeout(Duration::from_secs(2)).unwrap(), vec![1, 2]);
    }

    #[test]
    fn broken_socket_exits_without_waiting_for_producer() {
        let (tx, rx) = channel();
        let (done, result) = channel();
        tx.send(1).unwrap();
        tx.send(2).unwrap();
        thread::spawn(move || {
            let mut calls = 0;
            forward_events(rx, |_| {
                calls += 1;
                Err(io::Error::new(io::ErrorKind::BrokenPipe, "guest disconnected"))
            });
            done.send(calls).unwrap();
        });
        assert_eq!(result.recv_timeout(Duration::from_secs(2)).unwrap(), 1);
        assert!(tx.send(3).is_err());
    }
}
