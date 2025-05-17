public class Bst {
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }

    public static Node insert(Node root, int val){
        
        // first time insert
        if(root==null){
            return new Node(val);
        }

        if(root.data>val)
          root.left=insert(root.left, val);
        else
          root.right=insert(root.right, val);

        return root;
    }

    public static void inorder(Node root){
        if(root==null)
         return ;
         inorder(root.left);
         System.out.print(root.data+" ");
         inorder(root.right);
    }

    public static boolean search(Node root,int val){
        if(root==null){
            return false;
        }

        if(root.data==val)
          return true;
         if(root.data>val)
          return search(root.left, val);
        else
          return search(root.right, val);

    }

    public static Node findInorderSuccessor(Node root){
        
        while(root.left!=null)
          root=root.left;

        return root;
    }

    public static Node delete(Node root, int val){
        if(root==null){
            return  null;
        }

        if(root.data==val){
            // case 1: leaf Node
              if(root.left == null && root.right==null)
                 return null;
            // case 2: Single child
            //   a) left child 
              if(root.left==null)
               return root.right;
            //  b) right child
             else if(root.right==null)
                return root.left;
            // case 3: Two child
            else{
             Node IS=findInorderSuccessor(root.right); // leftmost node but in right subtree
             root.data=IS.data;
            //  deleting IS in right subtree and also updating right subtree
             root.right= delete(root.right, IS.data);
            }
        }

        else if(root.data>val)
          root.left=delete(root.left, val);
        else
          root.right=delete(root.right, val);
        
         return root;
    }

    public static void printInRange(Node root, int r1, int r2){
        if(root==null)
         return;

        if(root.data >= r1 && root.data <= r2){
            printInRange(root.left, r1, r2);
            System.out.print(root.data+" ");
            printInRange(root.right, r1, r2);
        }
        
        else if(root.data>r2){
            printInRange(root.left, r1, r2);
        }
        else {
            printInRange(root.right, r1, r2);
        }
    }

  public static void main(String[] args) {
    int nodes[]={8,5,3,1,4,6,10,11,14};
    Node root=null;
    for(int i=0;i<nodes.length;i++)
    root=insert(root, nodes[i]);
    inorder(root);
    System.out.println();
    // System.out.println( search(root, 1));
    // root=delete(root, 1);
    // inorder(root);
   printInRange(root, 5, 12);
 }  
}
