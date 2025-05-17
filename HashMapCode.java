import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.ArrayList;
public class HashMapCode {

    static class HashMap<K,V>{ //K,V are generics their type will be decided at runtimeand is not fixed

      private class Node{
            K key;
            V value;
            public Node(K key, V value){
                this.key=key;
                this.value=value;
            }
        }

        private int n;
        private int N; //n=size of hashmap ,N=size of array (buckets.length)
        private LinkedList<Node>[] buckets; //Array of linkedlist

        @SuppressWarnings("unchecked")//this annotation is used to suppress warnings to avoid warning about unchecked conversion
         public HashMap(){
            this.n=0;
            this.N=4;
            this.buckets=new LinkedList[4];// initialize Empty array of 4 buckets
            //initialize each bucket with an empty linkedlist

            /* |--> Above LinkedList type is expected in some versions of java but we have not specified it so compiler gives error therefore we use @SupressWarnings */
            for(int i=0;i<4;i++){
                this.buckets[i]=new LinkedList<>();
            }
         }

         private int hashFunction(K key){
            int rndmInt= key.hashCode();//It is a hashing function which returns random integer  
            return Math.abs(rndmInt) % N;//to make sure that the returned value lies between 0-3 (size of array)
         }
         private int searchInLL(K key, int bi){
              LinkedList<Node> ll=buckets[bi];

              int di=0;
              for(int i=0;i<ll.size();i++){
                if(ll.get(i).key == key){
                    return di;
                }
                di++;
              }
              return -1;
         }

         private void rehash(){
           LinkedList<Node> oldBucket[]= buckets; // to store data from buckets to oldbucket
            // update size of array
            N= 2*N;
            buckets = new LinkedList[N];//expand the size of array
            // initialize each bucket with an empty linkedlist
            for(int i=0;i<N;i++){
                buckets[i]=new LinkedList<>();
            }

            // Now transfer all elements from oldbucket to bucket
              for(int i=0;i<oldBucket.length;i++){
                LinkedList<Node> ll=oldBucket[i];
                for(int j=0 ; j<ll.size(); j++){
                      Node node=ll.remove();
                      put(node.key,node.value);
                }
              }
         }

         public void put(K key, V value){
            /*
             * 1) a hashfunction returns the bucket index 
             * 2) search for the node with key inside the LL of given bucketindex
             * 3) if found , update its value
             * 4) if not found , create a new node and add it to the LL
             * 5) increase size by one
             */
             int bucketIndx= hashFunction(key);
             int dataIndx=searchInLL(key, bucketIndx); // valid ; if -1 then no such key exists and invalid ; if >=0 then key already exists
             if(dataIndx != -1){
                 // update value since key already exists
                 Node node =buckets[bucketIndx].get(dataIndx);
                 node.value=value;
             }
             else{
                 // insert new node
                 buckets[bucketIndx].add(new Node(key, value));
                 n++;
             }

            //  rehashing
            double  lambda= (double)n/N;
            if(lambda > 2.0){ // lambda > threshold value then rehashing should happen
                rehash();
            }
         }

         public boolean conatinsKey(K key){
            int bucketIndx= hashFunction(key);
             int dataIndx=searchInLL(key, bucketIndx);

             if(dataIndx != -1){
                 return true;
             }
             else{
                return false;
             }
         }

         public boolean isEmpty(){
            return n==0;
         }

         public V remove(K key){
            int bucketIndx= hashFunction(key);
             int dataIndx=searchInLL(key, bucketIndx);

             if(dataIndx != -1){
                 Node node =buckets[bucketIndx].remove(dataIndx);
                 n--; //update number of nodes
                 return node.value;
             }
             else{
                 return null;
             }
         }

         public V get(K key){
            int bucketIndx= hashFunction(key);
            int dataIndx=searchInLL(key, bucketIndx);
            if(dataIndx != -1){
                Node node =buckets[bucketIndx].get(dataIndx);
                return node.value;
            }
            else{
               return null;
            }
         }

         public ArrayList<K> getKeys(){
            ArrayList<K> keys=new ArrayList<>();
            for(int i=0;i<N;i++){
                LinkedList<Node> ll=buckets[i];
                for(int j=0;j<ll.size();j++){
                    Node node=ll.get(j);
                    keys.add(node.key);
                }
            }
            return keys;
         }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map=new HashMap<>();
        map.put("India",50);
        map.put("Turkey",30);
        map.put("Indonesia",10);

        ArrayList<String> keys=map.getKeys();
        for(String key:keys){
            System.out.println(key+" "+map.get(key));
        }
    }
}