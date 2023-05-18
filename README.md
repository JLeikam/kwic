# KWIC (Keyword in Context) event-based model
The purpose of this project is to explore the event-based/implicit invocation implementation of the KWIC model, specifically the Indexing portion of it.

For more details see: [Garlan & Shaw's paper, An Introduction to Software Architecture](https://www.cs.cmu.edu/afs/cs/project/able/ftp/intro_softarch/intro_softarch.pdf)

The event-based approach follows the observer pattern.
In summary, each component can register an interest (i.e., subscribe) to an event, and also define a handler method to handle that event. Components can also publish events. So for example, Component A can subscribe to a "PROCESSING_COMPLETE" event and define a handleProcessingComplete method. Then Component B can publish its "PROCESSING_COMPLETE" event, and send along any relevant data for the subscribers of that event to process.

As discussed in Garlan and Shaw's paper, "the implicit invocation solution is particularly good for adding new functionality. However, it suffers from some of the problems of the shared data approach: poor support for change in data representation and reuse."

Disadvantages: there's a coupling between the components and the data passed between events. If we change the data format that a component sends, then all relevant components that listen to that data will need to be updated. There's also a performance overhead of managing the events and subscriptions rather than calling them directly. Ordering of events can also be an issue.

Advantages: It's easy to extend the system and add another component. New components can subscribe to the necessary events and define handlers. We also have nice encapsulation of components. If a piece of logic changes for a given component, we only have to modify that component, and the rest of the components simply receive the event as before.

The main takeaway is that there are always tradeoffs to consider.

Here are some helpful links for learning more about the Observer Pattern: 
- https://en.wikipedia.org/wiki/Observer_pattern 
- https://refactoring.guru/design-patterns/observer

# Getting started
1. Clone the repository
2. Navigate to the project directory
3. Run `mvn clean compile assembly:single`
4. Run `java -jar target/kwic-1.0-SNAPSHOT-jar-with-dependencies.jar`
5. Observe the output in the console
6. Update kwic-input.txt with your own input if desired and retry steps 3-5