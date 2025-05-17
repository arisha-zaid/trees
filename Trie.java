public class Trie { 
    static class Node{
        Node children[]=new Node[26];
        boolean  endOfWord=false;
        public Node(){
          for(int i=0; i < 26 ;i++){
              children[i]=null;
            }
        }
    }

    public static Node root=new Node();

    public static void insert(String word){
        Node curr=root;
        for(int level=0;level<word.length();level++){
            int indx=word.charAt(level)-'a';
            if(curr.children[indx] == null){
                curr.children[indx]=new Node();
            }
            curr= curr.children[indx];
        }
        curr.endOfWord=true;
    }

    public static boolean search(String key){
          Node curr=root;
          for(int level=0;level<key.length();level++){
            int indx= key.charAt(level)-'a';
            if(curr.children[indx] == null){
                return false;
            }
            curr=curr.children[indx];
          }
          return curr.endOfWord==true;
    }

    // Longest prefix word :
    public static String ans="";
    public static void longestWord(Node root,StringBuilder temp){
          if(root==null)
            return;
          for(int i=0;i<26;i++){
            char ch=(char)( i +'a');
               if(root.children[i]!=null && root.children[i].endOfWord==true){
                    temp.append(ch);
                    if(temp.length()>ans.length()){
                        ans=temp.toString();
                    }
                    longestWord(root.children[i],temp);
                    temp.deleteCharAt(temp.length()-1);
               }
          }
    }

    public static void main(String[] args) {
        String words[]= {"apple","app","a","ap","appl"};
        for(int i=0;i<words.length;i++){
            insert(words[i]);
        }
        System.out.println(search("appple"));
        System.out.println(search("man"));
        longestWord(root,new StringBuilder());
        System.out.println(ans);
    }
}
