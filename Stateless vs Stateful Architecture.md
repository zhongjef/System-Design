## Stateless architecture vs Stateful Architecture

A **stateless architecture** is where you store the client state not in the server (web tier), rather, the data tier (in a database along).

A **stateful architecture** is where the server remembers client state from one request to the next.

#### The benefit from going stateful to stateless:

- In a stateful architecture, when the load balancer directs user to a server that does not contain its     client state, the request must be routed to the specific server. Solutions include sticky session, where the load balancer remembers which user belongs to which server. 

On the other hand, if we do not store state in the server, then nothings needs to be routed.

- In a stateful architecture, it is challenging to handle server failure and scaling since careful handling of user routing must be handled.

On the other hand, stateless architecture **easily scales** horizontally since not must consideration must be taken towards client state.

**Example of stateful application:**

- Bank application

**Example of stateless application:**

- Single functionality web services that do not store data

 



#### Questions:

- Why do we want to store state isolated from other data?

- Decouple state from transaction

  Think about **event sourcing**. Event sourcing is where you do not store any state, but simply calculates the current state using the transactions made. Since we dont store state, no mutability problems (concurrency, deadlocks, etc) exists. In practice, we can update the state only in midnight, so that only a single day of transactions needs to be calculated.

  + Examples of event sourcing are source control systems and banking backend systems.

  The take away from event sourcing is that we can recover state from transactions if the state data and other data are loosely coupled.



#### Readings:

Article on stateless vs stateful:

- https://www.technologysolutionpartners.com/stateful-architecture-vs-stateless-architecture/

Netflix implementation of stateless user data in Active Active:

- https://netflixtechblog.com/active-active-for-multi-regional-resiliency-c47719f6685b

 

