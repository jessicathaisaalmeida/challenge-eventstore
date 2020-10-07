## Jéssica Almeida's Comments

For that Implementation, I chose to represent the In Memory Event Store 
as a HashMap structure, in order to improve queries by event type.
Once Thread-Security was an expected requirement, I used a ```ConcurrentHashMap``` to appropriate the
concurrency mechanisms Java already give us.

In that HashMap, the key is the event type and the value a list of
events. The list structure I used is an ArrayList, and for thread-safety
I used it with a ```Collections.synchronizedList```.

I could choose a different kind of list in the substructure, but I didn't because that choice would depend on the
principal usage of the EventStore.

For example, the usage of a TreeSet could be considered if duplications were not allowed. In that way, for a concurrent approach,
the usage of ```ConcurrentSkipListSet``` would be the most suitable. TreeSet structure implements a Red-Black Tree,
what in other words means that add, contains and remove operations has a log(n) cost.

Some project choices:
- I've created an implementation for the EventIterator, what would help me if I chose to change between the usage of
ArrayList structure and another one;
- Some parts of the code were wrapped by a synchronized operation, in order to garantee thread-safety
(from the tests I had written, that kind of concurrency error appeared);
- Most part of the Tests doesn't have a description, because their names are the most clear was possible.
I used the *shouldDoSomethingWhenSomething* naming pattern, for example:
 **shouldNotFailWhenRemovingAllEventsOfAnAbsentType** is a test that shouldn't fail when we are trying to
remove all elements of a not registered type. Sometimes the "When" part was omitted for clearly;
- As we could consider the in memory store approach as a hotspot, I chose to create a package for it. In that way,
another approaches could be created in different packages;
- The Tests organization, also had respected that hierarchical structure.

For doubts or contact, my email is jessicatsalmeida@gmail.com
___

# Implement EventStore

In this challenge, you will create a class that implements the `EventStore` 
interface.
 
```java
public interface EventStore {
    void insert(Event event);

    void removeAll(String type);

    EventIterator query(String type, long startTime, long endTime);
}
```

Your implementation should store events in memory, using any data structures 
you see fit for the task. The required behavior for the interface is described in the
provided code javadocs, please see `EventStore` and `EventIterator`
interfaces inside the `src/main/java` directory.
 
The implementation should be correct, fast, memory-efficient, and thread-safe. 
You may consider that insertions, deletions, queries, and iterations 
will happen frequently and concurrently. This will be a system hotspot. Optimize at will. 

We expect you to:
* Write tests;
* Provide some evidence of thread-safety;
* Justify design choices, arguing about costs 
  and benefits involved. You may write those as comments 
  inline or, if you wish, provide a separate document 
  summarizing those choices;
* Write all code and documentation in english.
  
You may use external libraries, but their use has to be 
properly justified as well.
 
This challenge is intentionally simple, we expect a simple,
elegant, and polished solution. There is no unique solution to this challenge. 
The intent is to evaluate candidate's coding proficiency and familiarity with 
tools and best practices.


## Solve this challenge

To solve this challenge, you may fork this repository, then 
send us a link with your implementation. Alternatively, if you do not want to have this repo on
your profile (we totally get it), send us a 
[git patch file](https://www.devroom.io/2009/10/26/how-to-create-and-apply-a-patch-with-git/) 
with your changes.

If you are already in the hiring process, you may send it to 
 whoever is your contact at Intelie. If you wish to apply for a job at 
 Intelie, please send your solution to [trabalhe@intelie.com.br](mailto:trabalhe@intelie.com.br).
