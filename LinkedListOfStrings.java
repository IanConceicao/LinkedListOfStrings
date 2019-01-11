//Ian Conceicao
public class LinkedListOfStrings {
   Node head;
   int nodeCount = 0;

   // iteratively traverse the Linked List, printing out the String + " --> "
   // unless it's the last node, in which case print out the String + " --> null"
   public String toString() {
      String listString = "Head: ";
      Node runner = head;
      while(runner != null) {
         listString += runner.name + " -----> ";
         runner = runner.next;
      }
      listString += "null";
      return listString;
   }

   // iteratively traverse the Linked List, counting out the number of Nodes
   // whose String contains str
   public int countNodesWithString(String str) {
      int count = 0;
      Node runner = head;
      while(runner != null) {
         if(runner.name.contains(str)) {
            count++;
         }
         runner = runner.next;
      }
      return count;
   }

   // recursively traverse the Linked List, counting out the number of Nodes
   // whose String contains str
   public int recursivelyCountNodesWithString(Node head, String str) {
      if(head == null) {
         return 0;
      }
      if(head.name.contains(str)) {
         return 1 + recursivelyCountNodesWithString(head.next, str);
      }
      return recursivelyCountNodesWithString(head.next, str);
   }

   // Prints the nodes in reverse, iteratively
   public void printReversed(Node head) {      
      Node runner = head;
      int count = 0;
      String listString = "null";

      //find how long it is
      while(runner != null) {
         count++;
         runner = runner.next;
      }

      String[] arr = new String[count];

      Node secondRunner = head;

      for(int i = 0; i < arr.length; i++) {
         arr[i] = secondRunner.name;
         secondRunner = secondRunner.next;
      }

      for(int j = arr.length - 1; j >= 0; j--) {
         listString += " <----- " + arr[j] ;
      }
      System.out.println(listString);

   } 

   // Prints the nodes in reverse, recursively
   public void recursivelyPrintReversed(Node head) {
      System.out.println(nextNode(head));
   }

   private String nextNode(Node head) {
      Node runner = head;
      if(runner == null) {
         return "null";
      }
      return nextNode(runner.next) + " <----- " + runner.name;
   }
    
   // Appends the specified element to the end of this list.
   // Returns true if this collection changed as a result of the call. 
   // (Returns false if this collection does not permit duplicates and already contains the specified element.) 
   public boolean add(Node n) {
      if(head == null) {
         head = n;
         return true;
      }
      return add(n, head);             
   }

   private boolean add(Node n, Node runner) {
      if(runner.next == null) {
         //switch 
         runner.next = n;
         return true;
      }
      return add(n, runner.next);
   }

   // Inserts the specified element at the specified position in this list.
   // Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
   public void add(int index, Node n) {
      Node runner = head;
      int nodeCount = 0;


      //count the number of nodes
      while(runner != null) {
         nodeCount++;
         runner = runner.next;
      }

      //makes sure index  less than or equal to number of nodes
      if(index > nodeCount || index < 0) {
         throw new IndexOutOfBoundsException();
      }

      //if it wants us to add to the end, or the thing is null
      if(index == nodeCount) {
         add(n);
         return;
      }

      //if it wants to add at beginning
      if(index == 0) {

         n.next = head;
         head = n;
         return;
      }

      runner = head;

      //get the runner to one before the desired node
      for(int i = 0; i < index - 1; i++) {
         runner = runner.next;
      }

      n.next = runner.next;
      runner.next = n;      
   }

   // Removes all of the elements from this list.
   public void clear() {
      head = null;
      this.size();
   }

   // Returns true if this list contains the specified element.
   public boolean contains(Node n) {
      Node runner = head;
      int nodeCount = this.size();

      for(int i = 0; i < nodeCount; i++) {
         if(runner.name.equals(n.name))
            return true;
         runner = runner.next;
      }

      return false;
   }

   // Returns the element at the specified position in this list
   // Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
   public Node get(int index) {
      Node runner = head;
      int nodeCount = 0;


      //count the number of nodes
      while(runner != null) {
         nodeCount++;
         runner = runner.next;
      }

      //makes sure index  less than or equal to number of nodes
      if(index >= nodeCount || index < 0) {
         throw new IndexOutOfBoundsException();
      }

      runner = head;

      //get the runner to one before the desired node
      for(int i = 0; i < index; i++) {
         runner = runner.next;
      }
      return runner;
   }

   // Removes the element at the specified position in this list.
   // Returns the element previously at the specified position
   // Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
   public Node remove(int index) {
      Node nodeToReturn = new Node("");
      Node runner = head;
      int nodeCount = this.size();

      //makes sure index  less than or equal to number of nodes
      if(index >= nodeCount || index < 0) {
         throw new IndexOutOfBoundsException();
      }

      //if it wants to remove the beginning
      if(index == 0) {
         nodeToReturn.name = head.name;
         head = head.next;
         return nodeToReturn;
      }

      runner = head;

      //get the runner to one before the desired node
      for(int i = 0; i < index - 1; i++) {
         runner = runner.next;
      }

      nodeToReturn.name = runner.next.name;
      runner.next = runner.next.next;
      return nodeToReturn;
   }

   // Removes the first occurrence of the specified element from this list, if it is present.
   // Returns true if this list contained the specified element (or equivalently, if this list changed as a result of the call).
   public boolean remove(Node n) {
      Node runner = head;
      int nodeCount = this.size();

      if(!this.contains(n)) {
         return false;
      }

      for(int i = 0; i < nodeCount; i++) {
         if(runner.name.equals(n.name)){
            this.remove(i);
            return true;
         }
         runner = runner.next;
      }
      return false;
   }

   // Replaces the element at the specified position in this list with the specified element.
   // Throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index >= size())
   public Node set(int index, Node n) {
      Node oldNode = new Node("");
      Node beforeOldNode = head;
      int nodeCount = this.size();

      //makes sure index  less than or equal to number of nodes
      if(index >= nodeCount || index < 0) {
         throw new IndexOutOfBoundsException();
      }

      //special case for head

     if(index == 0) {
        oldNode.name = head.name;
        System.out.println(oldNode.name);
        n.next = head.next;
        head = n;
        return oldNode;
     }
      
      for(int i = 0; i < index - 1; i++) {
         beforeOldNode = beforeOldNode.next;
      }
      oldNode = beforeOldNode.next;
       
      beforeOldNode.next = n;
      n.next = oldNode.next;
      
      return oldNode;
   }

   //Returns the number of elements in this collection.
   public int size() {
      nodeCount = 0;
      Node runner = head;

      while(runner != null) {
         nodeCount++;
         runner = runner.next;
      }

      return nodeCount;
   }
}
