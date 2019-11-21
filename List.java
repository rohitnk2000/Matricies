//-----------------------------------------------------------------------------
//  List.java
//  A bi-directional integer queue ADT
// 
//  Rohit Kulkarni, 1654070, 4/11/19, pa1
//
//-----------------------------------------------------------------------------

public class List{

   private class Node{
      // Fields
      Object data;
      Node next;
      Node prev;
      
      // Constructor
      Node(Object data) { this.data = data; next = null; prev = null; }
      
      // toString():  overrides Object's toString() method
      public String toString() { 
         return data.toString(); 
      }
      
      // equals(): overrides Object's equals() method
      public boolean equals(Object x){
         boolean eq = false;
         Node that;
         if(x instanceof Node){
            that = (Node) x;
            eq = (this.data.equals(that.data));
         }
         return eq;
      }
   }

   // Fields
   private Node cursor;
   private Node front;
   private Node back;
   private int length;
   private int index;

   // Constructor
   public List() { 
      front = back = null; 
      cursor = null;
      length = 0; 
   }


   // Access Functions --------------------------------------------------------

   // isEmpty()
   // Returns true if this Queue is empty, false otherwise.
   boolean isEmpty() { 
      return length==0; 
   }

   // getLength()
   // Returns length of this Queue.
   int length() { 
      return length; 
   }
   
   //index()
   //If cursor is defined, returns the index of the cursor element,
   // otherwise returns -1.
   int index()
   {
	   if(cursor == null)
	   {
		   return -1;
	   }
	   return index;
   }

   // front() 
   // Returns front element.
   // Pre: !this.isEmpty()
   Object front(){
      if( length <= 0 ){
         throw new RuntimeException(
            "Queue Error: front() called on empty Queue");
      }
      return front.data;
   }
   //back()
   //Returns back element
   //Pre: !this.isEmpty()
   Object back() {
	   if( length <= 0 ){
	         throw new RuntimeException(
	            "Queue Error: back() called on empty Queue");
	      }
	   return back.data;
   }
   
   //get()
   //returns element at cursor
   //Pre: cursor is at null
   Object get() 
   {
	   if( length < 0 ){
	         throw new RuntimeException(
	            "Queue Error: get() called on empty Queue");
	     }
	   else if(index() < 0)
	   {
		   throw new RuntimeException("Queue Error: get() called with null cursor");
	   }
	   return cursor.data;
	   
   }
   
   // equals()
   // Overrides Object's equals() method.  Returns true if x is a Queue storing
   // the same integer sequence as this Queue, false otherwise.
   public boolean equals(Object x){
      boolean eq  = false;
      List Q;
      Node N, M;

      if(x instanceof List){
         Q = (List)x;
         N = this.front;
         M = Q.front;
         eq = (this.length==Q.length);
         while( eq && N!=null ){
            eq = N.equals(M);
            N = N.next;
            M = M.next;
         }
      }
      return eq;
   }
   

   // Manipulation Procedures -------------------------------------------------

   //clear()
   // Resets this List to its original empty state.
   void clear()
   {
	   if(!this.isEmpty())
	   {
		   front = back = null;
		   cursor = null;
		   length = 0;
	   }
   }
   
 
   //moveFront()
   // If List is non-empty, places the cursor under the front element,
   // otherwise does nothing.
   void moveFront()
   {
	   cursor = front;
	   index = 0;
   }
   //moveBack()
   // If List is non-empty, places the cursor under the back element,
   // otherwise does nothing.
   void moveBack()
   {
	   if(length >= 0)
	   {
		   cursor = back;
		   index = length - 1;
	   }
   }
   
   void movePrev()
   {
	   if(cursor != null)
	   {
		   if(cursor == front)
		   {
			   cursor = null;
		   }
		   else
		   {
			   cursor = cursor.prev;
			   index--;
		   }
	   }
   }
   
   void moveNext()
   {
	   if(cursor != null)
	   {
		   if(cursor == back)
		   {
			   cursor = null;
		   }
		   else
		   {
			   cursor = cursor.next;
			   index++;
		   }
	   }
   }
   
   void prepend(Object data)
   {
	   Node N = new Node(data);
	   if(this.isEmpty())
	   {
		   front = back = N;
	   }
	   else
	   {
		   front.prev = N;
		   N.next = front;
		   front = N;
		   if(cursor != null)
		   {
			   index++;
		   }
	   }
	   length++;
	   
   }
   
   // Enqueue()
   // Appends data to back of this Queue.
   void append(Object data){
	   Node N = new Node(data);
	      if( this.isEmpty() ) { 
	         front = back = N;
	      }else{ 
	         back.next = N;
	         N.prev = back;
	         back = N; 
	      }
	      length++;
	      
   }
   
   void insertBefore(Object data)
   {
	   if(this.length <= 0)
	   {
		   throw new RuntimeException("insertBefore cannot be called on empty list");
	   }
	   else if(this.index() < 0)
	   {
		   throw new RuntimeException("cursor is not set");
	   }
	   else
	   {
		   Node N = new Node(data);
		   if (cursor.prev==null){
	            N.next = cursor;
	            N.prev = null;
	            front = N;
	            cursor.prev = N;
	       }
		   else
		   {
			   N.prev = cursor.prev;
			   N.next = cursor; 
			   cursor.prev = N;
			   N.prev.next = N;
		   }
		   index++;
		   length++;
	   }
   }
   
   void insertAfter(Object data)
   {
	   if(this.length <= 0)
	   {
		   throw new RuntimeException("insertAfter cannot be called on empty list");
	   }
	   else if(this.index() < 0)
	   {
		   throw new RuntimeException("cursor is not set");
	   }
	   else
	   {
		   Node N = new Node(data);
           if(cursor.next == null)
           {
        	   cursor.next = N;
        	   back = N;
        	   N.prev = cursor;
           }
           else
           {
        	   N.next = cursor.next;
        	   N.prev = cursor;
        	   N.next.prev = N;
        	   cursor.next = N;
           }
           // update length.
           length++;
           
	   }
	   
   }
   
   void deleteFront()
   {
	   if(length <= 0)
	   {
		   throw new RuntimeException("deleteFront cannot be called on empty list");
	   }
	   if(length > 1)
	   {
		   front = front.next;
	   }
	   else if(length == 1)
	   {
		   front = back = null;
	   }
	   length--;
	   index--;

   }
   
   void deleteBack()
   {
	   if(length < 0)
	   {
		   throw new RuntimeException("deleteBack cannot be called on Emtpy list");
	   }
	   if(cursor == back)
	   {
		   cursor = null;
	   }
	   if(length == 0)
	   {
		   front = back = null;
	   }
	   back = back.prev;
	   
	   length--;
	   
   }
   
   void delete()
   {
	   if(length > 0 && this.index() >= 0)
	   {
		   if(index() == 0)
		   {
			   front = front.next;
			   front.prev = null;
			   length--;
		   }
		   else if(index() == this.length - 1)
		   {
			   back = back.prev;
			   back.next = null;
			   length--;
		   }
		   else
		   {
			   cursor.prev.next = cursor.next;
			   cursor.next.prev = cursor.prev;
			   length--;
		   }
		   cursor = null;
	   }
	   
   }

   


   // Other Functions ---------------------------------------------------------

   // toString()
   // Overides Object's toString() method.
   public String toString(){
      StringBuffer sb = new StringBuffer();
      Node N = front;
      while(N!=back){
         sb.append(N.toString());
         sb.append(" ");
         N = N.next;
      }
      if(N!=null)sb.append(back.toString());
      return new String(sb);
   }

 

   // copy()
   // Returns a new Queue identical to this Queue.
   List copy(){
      List Q = new List();
      Node N = this.front;

      while( N!=null ){
         Q.append(N.data);
         N = N.next;
      }
      return Q;
   }

}
