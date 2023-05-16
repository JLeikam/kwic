# KWIC (Keyword in Context) event-based model
The purpose of this project is to explore the event-based/implicit invocation implementation of the KWIC model, specifically the Indexing portion of it.

For more details see: [Garlan & Shaw's paper, An Introduction to Software Architecture](https://www.cs.cmu.edu/afs/cs/project/able/ftp/intro_softarch/intro_softarch.pdf)

# Getting started
1. Clone the repository
2. Navigate to the project directory
3. Run `mvn clean compile assembly:single`
4. Run `java -jar target/kwic-1.0-SNAPSHOT-jar-with-dependencies.jar`
5. Observe the output in the console
6. Update kwic-input.txt with your own input if desired and retry steps 3-5