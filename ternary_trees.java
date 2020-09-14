public class ternary_trees {

    //insert
    // search
    // delete
    // traverse
    // prefix_check
    // prefix_print

    static class trieNode
    {
        char data;
        boolean eow=true;
        trieNode left,eq,right;
        trieNode(char data)
        {
            this.data=data;
            eow=false;
            left=right=eq=null;
        }
    }
    static trieNode insert(trieNode root,String word,int i)
    {
        if(root==null) root=new trieNode(word.charAt(i));

        if(word.charAt(i)<root.data) root.left=insert(root.left,word,i);
        else if(word.charAt(i)>root.data) root.right=insert(root.right,word,i);
        else
        {
            if(i+1<word.length()) root.eq=insert(root.eq,word,i+1);
            else root.eow=true;
        }
        return root;
    }
    public static boolean search(trieNode root,String word,int i)
    {
        if(root==null) return false;
        if(word.charAt(i)<root.data) return search(root.left,word,i);
        if(word.charAt(i)>root.data) return search(root.right,word,i);
        if(i+1<word.length()) return search(root.eq,word,i+1);
        return root.eow;
    }

    public static trieNode delete(trieNode root,String word,int i)
    {
        if(root==null) return root;

        char ch=word.charAt(i);
        int n=word.length();

        if(ch<root.data)  root.left = delete(root.left,word,i);
        else if(ch>root.data) root.right = delete(root.right,word,i);

        else if(ch==root.data)
        {
            if(i==n-1)
            {
                if(root.eow==false) return root;
                //if(root.eow==true) root.eow=false;
                root.eow=false;
            }
            else root.eq=delete(root.eq,word,i+1);

            if(root.eow==false)
            {
                if(root.eq==null)
                {
                    if(root.left==null) root=root.right;
                    else if(root.right==null) root=root.left;
                    else
                    {
                        root=root.left;
                        root.left=null;
                    }
                }
            }
        }
        return root;
    }
    public static void deleteNode(trieNode root,String word,int i)
    {
        if(!search(root,word,0)) return;
        else  delete(root,word,0);

    }

    public static void traverse(trieNode root,String s1,String prefix)
    {
        if(root==null) return;
        if(root.eow==true) System.out.println(prefix+s1+root.data);
        traverse(root.left,s1,prefix);
        traverse(root.eq,s1+root.data,prefix);
        traverse(root.right,s1,prefix);

    }
    public static boolean startwith(trieNode root,String prefix,int i)
    {
        if(root==null) return false;
        char ch=prefix.charAt(i);
        if(ch<root.data) return startwith(root.left,prefix,i);
        if(ch>root.data) return startwith(root.right,prefix,i);
        if(i<prefix.length()-1) return startwith(root.eq,prefix,i+1);
        if(i==prefix.length()-1) return true;
        return false;
        //return startwith(root.eq,prefix,i+1);
    }
    public static void prefix_print(trieNode root,String prefix,int i)
    {
        if(root==null) return;

        if(prefix.charAt(i)<root.data) prefix_print(root.left,prefix,i);
        else if(prefix.charAt(i)>root.data) prefix_print(root.right,prefix,i);
        else if(i<prefix.length()-1) prefix_print(root.eq,prefix,i+1);
        else traverse(root.eq,"",prefix);
    }

    public static void main(String[] args)
    {
        trieNode root=null;
        //String s[]={"Prakriti","Ragh","Raghav","Rashi","Sunidhi"};
        String s[]={"bugs","cat","cats","up"};
        //String s[]={"dc","ba","eb"};
        for(int i=0;i<s.length;i++)
        {
            root=insert(root,s[i],0);
        }
        for(int i=0;i<s.length;i++) System.out.println(s[i]+" is present?? --> "+search(root,s[i],0));
        System.out.println("Prakri is present?? --> "+search(root,"Prakri",0));
        System.out.println("pfix "+startwith(root,"Ra",0));

        System.out.println();
        traverse(root,"","");

        System.out.println();
        //prefix_print(root,"Ra",0);
        prefix_print(root,"ca",0);

        System.out.println("------------------------------------------------");

        for(int i=0;i<s.length;i++)
        {
              System.out.println("-----"+s[i]+" deleted");
            deleteNode(root,s[i],0);

            traverse(root,"","");
            System.out.println();
        }
    }
}
