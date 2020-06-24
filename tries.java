import java.util.*;
public class tries {

    // INSERT
    // SEARCH
    // DELETE
    // DISPLAY DISPLAY_DOWN_TO_UP
    // PREFIX
    // PREFIX1

    static class TrieNode
    {
        HashMap<Character,TrieNode> hm;
        boolean eow;

        TrieNode()
        {
            hm=new HashMap<>();
            eow=false;
        }
    }
    public static void insert(TrieNode node,String[] s)
    {
        int n=s.length;
        for(int i=0;i<n;i++)
        {
            TrieNode t1=node;
            String s1=s[i];
            int m=s1.length();

            for(int j=0;j<m;j++)
            {
               if(!t1.hm.containsKey(s1.charAt(j)))
               {
                   t1.hm.put(s1.charAt(j),new TrieNode());
               }

                t1=t1.hm.get(s1.charAt(j));
                if(j==m-1) t1.eow=true;
            }
        }
    }
  public static boolean search(TrieNode node,String s1,int i)
    {
        if(i==s1.length() && node.eow==false) return false;
        if(i==s1.length()) return true;
        if(!node.hm.containsKey(s1.charAt(i))) return false;

        return search(node.hm.get(s1.charAt(i)),s1,i+1);

    }
    public static boolean delete(String s,int i,TrieNode n1)
    {
        if(i==s.length())
        {
            if(!n1.eow) return false;
            n1.eow=false;

            return (n1.hm.size()==0);

        }
        if(!n1.hm.containsKey(s.charAt(i))) return false;
        boolean b1=delete(s,i+1,n1.hm.get(s.charAt(i)));

        if(b1==true)
        {
            n1.hm.remove(s.charAt(i));
            return (n1.hm.size()==0 && !n1.eow);
        }
        return false;
    }


    public static void display(TrieNode n1,String s1,String prefix)
    {
        if(n1.eow) System.out.println(prefix+s1);
        if(n1.hm.size()==0) return;

        for(Map.Entry<Character,TrieNode> mp:n1.hm.entrySet())
        {
            char c1=mp.getKey();
            TrieNode t1=mp.getValue();

            display(t1,s1+c1,prefix);
        }

    }
    public static void display_down_to_up(TrieNode node)
    {
        if(node.hm.size()==0) return;
        for(Map.Entry<Character,TrieNode> mp:node.hm.entrySet())
        {
            char c1=mp.getKey();
            TrieNode t1=mp.getValue();

            display_down_to_up(t1);
            System.out.print(c1);

           /* display_down_to_up(t1,s1+c1);
            if(node.eow==true) System.out.print(s1+" ");*/

        }
    }
    public static boolean prefix(TrieNode n1,String pfix)
    {
        TrieNode t1=n1;
        for(int i=0;i<pfix.length();i++)
        {
            if(!t1.hm.containsKey(pfix.charAt(i))) return false;
            t1=t1.hm.get(pfix.charAt(i));
        }
        return true;
    }

    public static void prefix1(TrieNode n1,String prefix,int i)
    {
        if(i==prefix.length())
        {
            display(n1,"",prefix);
            return;
        }
        if(!n1.hm.containsKey(prefix.charAt(i))) return;
        prefix1(n1.hm.get(prefix.charAt(i)),prefix,i+1);
    }

    public static void main(String[] args)
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();

        TrieNode node=new TrieNode();

        String[] s=new String[n];
        for(int i=0;i<n;i++) s[i]=sc.next();

        insert(node,s);

        display(node,"","");
        System.out.println();

        display_down_to_up(node);
        System.out.println('\n');

        for(int i=0;i<n;i++)
        {
            System.out.println(search(node,s[i],0));
        }

        System.out.println();
        prefix1(node,"a",0);
        System.out.println();
        prefix1(node,"abc",0);
        System.out.println();
        prefix1(node,"ab",0);
        System.out.println();
        prefix1(node,"cd",0);
        System.out.println();
        /*delete("lnm",0,node);
        delete("lmn",0,node);
        delete("abcd",0,node);

        System.out.println();
        for(int i=0;i<n;i++)
        {
            System.out.println(s[i]+" "+search(node,s[i],0));
        }*/

    }
}
/*
5
abc
abgl
cdf
abcd
lmn
* */