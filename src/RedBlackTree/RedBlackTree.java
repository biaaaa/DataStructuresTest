package RedBlackTree;

public class RedBlackTree <T extends Comparable<T>>{

	    private RedBlackNode<T> root; //root node
	    private int size;
	    private final RedBlackNode<T> sentinel=new RedBlackNode<T>(null, BLACK, null, null, null);
	    private static final boolean RED = false; //the sign of red and black
	    private static final boolean BLACK = true;  
	      
	    //node class
	    public static class RedBlackNode<T extends Comparable<T>>{  
	        	
	        private T key;
	        private boolean color;
	        private RedBlackNode<T> left; //left child
	        private RedBlackNode<T> right; //right child
	        private RedBlackNode<T> parent;
	          
	        public RedBlackNode(T key, boolean color, RedBlackNode<T> parent, RedBlackNode<T> left, RedBlackNode<T> right) {  
	            this.key = key;  
	            this.color = color;  
	            this.parent = parent;  
	            this.left = left;  
	            this.right = right;  
	        }

			public T getKey() {
				return key;
			}
			
			@Override
			public String toString(){
				return key+(color?", BLACK":", RED")+", Parent: "+parent.key+", Left: "+left.key+", Right "+right.key;
			}
	    }
	    
	    //Constructor
	    public RedBlackTree(){
	    	root=sentinel;
	    	size=0;
	    }
	    
	    public int size(){
	    	return size;
	    }
	    
	    /*************************Preorder*************************/
	    public void preOrder() {  
	        preOrder(root);  
	    }  
	  
	    private void preOrder(RedBlackNode<T> node) {  
	        if(node != null&&node!=sentinel) {  
	            System.out.println(node.key + (node.color?" BLACK":" RED")+" ");
	            
	            preOrder(node.left);  
	            preOrder(node.right);  
	        }  
	    }  
	    
	    
	    /*************************Start Insert*************************/
	    
	    public boolean insert(T key){
	    	if(key==null){
	    		return false;
	    	}
	    	RedBlackNode<T> node = new RedBlackNode<T>(key, RED, null, sentinel, sentinel);
	    	//preOrder();
	    	//System.out.println("");
	    	if(insert(node)){
	    		size++;
	    	}
	    	return insert(node);
	    }
	    
	    private boolean insert(RedBlackNode<T> node){
	    	if(node==null){
	    		return false;	//nothing to insert
	    	}

	    	RedBlackNode<T> c=root;
	    	RedBlackNode<T> p=null;
	    	//find the place for new node;
	    	while(c!=sentinel){
	    		p=c;
	    		if(node.key.compareTo(c.key)<0){
	    			c=c.left;
	    		}else if(node.key.compareTo(c.key)>0){
	    			c=c.right;
	    		}else{
	    			//throw NodeHasExistedException;
	    			return false;
	    		}
	    	}
	    	//set its parent and select its parent's left or right
	    	node.parent=p;
	    	if(p==null){
	    		root=node;
	    	}else{
		    	if(node.key.compareTo(p.key)<0){
		    		p.left=node;
		    	}else{
		    		p.right=node;
		    	}
	    	}
	    	
	    	insertFix(node);
	    	
	    	return true;

	    }
	    
	    private void insertFix(RedBlackNode<T> node){
	    	RedBlackNode<T> parent=node.parent;

	    	if(parent!=null&&parent.color==RED){
		    	RedBlackNode<T> grandparent=parent.parent;
		    	if(grandparent!=null){
		    		RedBlackNode<T> uncle=null;
		    		if(grandparent.left==parent){
		    			uncle=grandparent.right;
		    			if(uncle!=sentinel&&uncle.color==RED){						//case 1
			    			uncle.color=BLACK;
			    			parent.color=BLACK;
			    			grandparent.color=RED;
			    			insertFix(grandparent);
			    		}
			    		else if(node == parent.right) {  							//case 2
			                leftRotate(parent);  
			                insertFix(parent);
			    		}
			    		else{														//case 3
			    			parent.color=BLACK;
			    			grandparent.color=RED;
			    			rightRotate(grandparent);
			    			insertFix(node);
			    		}
		    		}
		    		else{
		    			uncle=grandparent.left;
		    			if(uncle!=sentinel&&uncle.color==RED){						//case 1
			    			uncle.color=BLACK;
			    			parent.color=BLACK;
			    			grandparent.color=RED;
			    			insertFix(grandparent);
			    		}
			    		else if(node == parent.left) {  							//case 2
			                rightRotate(parent);  
			                insertFix(parent);
			    		}
			    		else{														//case 3
			    			parent.color=BLACK;
			    			grandparent.color=RED;
			    			leftRotate(grandparent);
			    			insertFix(node);
			    		}
		    		}
		    	}
	    	}
	    	root.color=BLACK;
	    }
	    
	    /*************************Finish Insert*************************/
	    
	    /*************************Start Delete*************************/
	    //Delete
	    public boolean delete(RedBlackNode<T> node){
	    	return true;
	    }
	    /*************************Finish Delete*************************/
	    
	    
	    /*************************Start Search*************************/
	    //Search
	    public RedBlackNode<T> search(T key){
	    	if(key==null){
	    		return null;
	    	}
	    	RedBlackNode<T> p=root;
	    	while(p.key!=null){
	    		if(key.compareTo(p.key)<0){
	    			p=p.left;
	    		}else if(key.compareTo(p.key)>0){
	    			p=p.right;
	    		}
	    		else{
	    			return p;
	    		}
	    	}
	    	return null;
	    }
	    
	    
	    /*************************Finish Search*************************/
	    
	    
	    
	    //private tools
	    private boolean isLeftChild(RedBlackNode<T> node){
	    	if(node==null||node.parent==null){
	    		return false;
	    	}
	    	if(node==node.parent.left){
	    		return true;
	    	}
	    	return false;
	    }
	    
	    private boolean isRightChild(RedBlackNode<T> node){
	    	if(node==null||node.parent==null){
	    		return false;
	    	}
	    	if(node==node.parent.right){
	    		return true;
	    	}
	    	return false;
	    }
	    
	    /*
	     * 		a               c
	     *     / \             / \
	     *    b   c     =>    a   y
	     *       / \         / \
	     *      x   y       b   x
	     *   
	     */
	    private boolean leftRotate(RedBlackNode<T> node){
	    	if(node==null||node==sentinel||node.right==sentinel){
	    		return false;
	    	}
	    	
	    	RedBlackNode<T> right=node.right;
	    	
	    	node.right=right.left;
	    	if(right.left!=sentinel){
	    		right.left.parent=node;
	    	}
	    	
	    	right.parent=node.parent;
	    	if(isLeftChild(node)){
	    		node.parent.left=right;
	    	}
	    	else if(isRightChild(node)){
	    		node.parent.right=right;
	    	}else{
	    		root=right;
	    	}
	    	
	    	right.left=node;
	    	node.parent=right;
	    	
	    	return true;
	    }
	    
	    private boolean rightRotate(RedBlackNode<T> node){
	    	if(node==null||node==sentinel||node.left==sentinel){
	    		return false;
	    	}
	    	
	    	RedBlackNode<T> left=node.left;
	    	
	    	node.left=left.right;
	    	if(left.right!=sentinel){
	    		left.right.parent=node;
	    	}
	    	
	    	left.parent=node.parent;
	    	if(isLeftChild(node)){
	    		node.parent.left=left;
	    	}
	    	else if(isRightChild(node)){
	    		node.parent.right=left;
	    	}else{
	    		root=left;
	    	}
	    	
	    	left.right=node;
	    	node.parent=left;
	    	
	    	return true;
	    }
	    
}
