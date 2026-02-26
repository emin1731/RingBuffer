# Ring Buffer

## Project overview

This project implements a fixed-size ring buffer where one writer appends values and multiple readers consume values independently.

Key behavior:

- The Ring Buffer has a fixed capacity N.
- Only one writer is allowed to call write().
- There may be multiple readers, each reading from the same buffer.
- Each reader must have its own reading position.
- Reading by one reader must not remove the item for other readers.
- If the buffer becomes full, the writer is allowed to overwrite the oldest data.
- In this case, slow readers may miss some items.

## Design and responsibilities

### `RingBuffer`

- Owns the storage arrays (`buffer`, `sequences`) and capacity.
- Owns global write sequence (`writeSequence`).
- Handles write placement with modulo indexing.
- Validates whether a requested sequence is still available.
- Uses synchronized methods for thread-safe access to shared state.

### `Writer`

- Represents the producer role.
- Sends values into `RingBuffer` through `write(int value)`.

### `Reader`

- Represents one consumer with an independent cursor (`readSequence`).
- Reads from `RingBuffer` by sequence.
- Detects missed/overwritten data (`null` read) and catches up to the oldest currently available sequence.

### `Main`

- Entry point for running the program.
- Currently a placeholder and can be extended with a scenario to demonstrate writer/reader interactions.

## UML Class Diagram

![UML Class Diagram](assets/Class%20Diagram%20-%20RingBuffer.jpg)

Diagram source: [Miro board](https://miro.com/app/board/uXjVG8bkPB0=/?share_link_id=440724499588)

## UML Sequence Diagram - `write()`

![UML Sequence Diagram - write()](assets/Sequence%20Diagram%20-%20Writer.jpg)

Diagram source: [Miro board](https://miro.com/app/board/uXjVG8bkPB0=/?share_link_id=440724499588)

## UML Sequence Diagram - `read()`

![UML Sequence Diagram - read()](assets/Sequence%20Diagram%20-%20Reader.jpg)

Diagram source: [Miro board](https://miro.com/app/board/uXjVG8bkPB0=/?share_link_id=440724499588)

## How to run

1. Open a terminal in the project folder.
2. Compile:

```bash
javac Main.java RingBuffer.java Writer.java Reader.java
```

3. Run:

```bash
java Main
```

## How to test behavior manually

To validate ring-buffer behavior, add a short scenario in `Main.main` that:

1. Creates one `RingBuffer` with small size (for example `3`).
2. Creates one `Writer` and at least two `Reader` instances.
3. Writes more than capacity (for example 5-6 values).
4. Reads at different speeds from readers.
5. Observes that:
   - Readers progress independently.
   - Reading by one reader does not remove values for another.
   - Slow readers receive `null` after overwrite and then catch up.

## Notes

- Current implementation is thread-safe for shared buffer state through synchronization.
- Single-writer usage is expected by design (`Writer` role), while strict runtime enforcement in `RingBuffer` can be added as a next improvement.
