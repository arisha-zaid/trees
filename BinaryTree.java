import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class BinaryTree{

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

   static class binaryTree{
        static int indx=-1;
      public static Node buildTree(int nodes[]){

        indx++;
        if(nodes[indx]==-1){
            return null;
        }
        Node newNode=new Node(nodes[indx]);
        newNode.left=buildTree(nodes);
        newNode.right=buildTree(nodes);

        return newNode;
      }



     public static void preorder(Node root){
        if(root==null){
            // System.out.print("-1, ");
            return;
        }
        System.out.print(root.data+", ");
        preorder(root.left);
        preorder(root.right);
     }

     public static void inOrderN(Node root){
        Stack<Node> s1=new Stack<>();
        Stack<Integer> s2=new Stack<>();
        Node curr=root;

        while(!s1.isEmpty()){
        // traverse leftmost Node 
        while(curr.left!=null){
            curr=curr.left;
        }
        s1.push(curr);

        Node poppedNode=s1.pop();
        // s2.push(poppedNode.data);
        System.out.print(poppedNode.data+", ");

        while(poppedNode.right!=null){
            poppedNode=poppedNode.right;
        }
        s1.push(poppedNode);
    }
    // while(!s2.isEmpty()){
    //     System.out.print(s2.peek()+", ");
    //     s2.pop();
    // }

     }

     public static void inorder(Node root){
        if(root==null){
            // System.out.print("-1, ");
            return;
        }
        inorder(root.left);
        System.out.print(root.data+", ");
        inorder(root.right);
     }

     public static void postOrderN(Node root){
        Stack<Node> s1=new Stack<>();
        Stack<Integer> s2=new Stack<>();

        s1.push(root);

        while(!s1.isEmpty()){
            Node curr=s1.pop();
            s2.push(curr.data);
            if(curr.left!=null){
                s1.push(curr.left);
            }
            if(curr.right!=null){
                s1.push(curr.right);
            }
        }

        while(!s2.isEmpty()){
            System.out.print(s2.peek()+", ");
            s2.pop();
        }
     }

     public static void postorder(Node root){
        if(root==null){
            // System.out.print("-1, ");
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data+", ");
     }

    //  Level order traversal using queue and iterative method
    public static void levelorder(Node root){
        //base case 
        if(root==null){
            return;
        }

        Queue<Node> q=new LinkedList<>();
        q.add(root);
        q.add(null);

        while(!q.isEmpty()){
            Node curr=q.remove();

            // Next line
            if(curr==null){
              System.out.println();
              if(q.isEmpty()){
                break;
              }else{
                q.add(null);
              }
            }else{
               System.out.print(curr.data+" ");
               if(curr.left!=null){
                q.add(curr.left);
               }
               if(curr.right!=null){
                q.add(curr.right);
               }
            }
        }
    }

    public static int height(Node root){
        if(root==null){
            return 0;
        }

        int lh=height(root.left);
        int rh=height(root.right);
        return Math.max(lh, rh)+1;
    }

    public static int countNodes(Node root){
        if(root==null){
            return 0;
        }

        int ln=countNodes(root.left);
        int rn=countNodes(root.right);
        return  ln + rn +1;
    }

     public static int sumNodes(Node root){
        if(root==null){
            return 0;
        }
        int leftsum=sumNodes(root.left);
        int rightsum=sumNodes(root.right);
        return leftsum +rightsum +root.data;
     }
   }

    public static void main(String Args[]){
      int nodes[]={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
      binaryTree tree=new binaryTree();
      Node root=tree.buildTree(nodes);
    //   System.out.println(root.data);
    //   tree.preorder(root);
    //   System.out.println();
      System.out.println("Inorder");
      tree.inorder(root);
    //   System.out.println();
    //   System.out.println("Postorder");
    //   tree.postorder(root);
    //   System.out.println();
    //   System.out.println("LevelOrder");
    //   tree.levelorder(root);
    // System.out.println(tree.height(root));
    // System.out.println(tree.countNodes(root));
    // System.out.println(tree.sumNodes(root));
    // System.out.println();
    // tree.postOrderN(root);
    System.out.println();
    tree.inOrderN(root);
    }
}
