# Ring Buffer

### Assignment description

Ring Buffer (Multiple Readers, Single Writer)
You implemented a basic Ring Buffer with write() and read() methods.

For this assignment, you must redesign your solution to support:

Single Writer
Multiple Readers
Readers must be able to read independently
Requirements
The Ring Buffer has a fixed capacity N.
Only one writer is allowed to call write().
There may be multiple readers, each reading from the same buffer.
Each reader must have its own reading position.
Reading by one reader must not remove the item for other readers.
If the buffer becomes full, the writer is allowed to overwrite the oldest data.
In this case, slow readers may miss some items.

      Your solution must be designed using proper OO principles:

clear responsibilities
clean class structure
no “everything in one class” design
Deliverables (GitHub Repository)
You must submit a GitHub repository that contains:

A complete Java implementation
A README.md file that includes:
a short project overview
explanation of the design (responsibilities of main classes)
UML Class Diagram
UML Sequence Diagram for write()
UML Sequence Diagram for read()
instructions on how to run/test the project
