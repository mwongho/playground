
A Spring Batch job that aggregates data by the tuple from a csv file and outputs the tuple with the count and the sum of amounts.

### Frameworks and Libraries used:

- Spring Boot - Application container
- Spring Batch - Batch processing 
- OpenCSV - Reading and Writing CSV file
- Project Lombok - Boilerplate code

### Requirements:

- No use of SQL
- No use of aggregate libraries

### Assumptions:

- Functional programming can be used
- Input data file will be small
- Input data file will always be correct and consistent
- No duplicate data
- Not appending to output file

### Performance and scaling :

> Spring Batch provides reusable functions that are essential in processing large volumes of records, including logging/tracing,
> transaction management, job processing statistics, job restart, skip, and resource management. It also provides more advanced 
> technical services and features that will enable extremely high-volume and high performance batch jobs through optimization and 
> partitioning techniques. Simple as well as complex, high-volume batch jobs can leverage the framework in a highly scalable manner 
to process significant volumes of information.

> ### Features

> - Transaction management
> - Chunk based processing
> - Declarative I/O
> - Start/Stop/Restart
> - Retry/Skip
> - Web based administration interface

If you want to large process large files you would want to Chunk the data and persist it