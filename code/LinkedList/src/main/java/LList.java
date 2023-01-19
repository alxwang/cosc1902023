public class LList <T>{
    private Node<T> head;

    public LList(){ head = null;}
    public LList(T data){
        head = new Node<T>(data);
    }

    public void addTail(T data){
        Node<T> node = new Node<>(data);
        if(head==null)//The list is empty
          head=node;
        else {
            System.out.printf("%s.setNext(Addtail(%s,%s))\n",head,head.getNext(),node);
            Node<T> rs = addTail(head.getNext(), node);
            System.out.printf("%s.setNext(%s)\n",head,rs==null?"NULL":rs);
            head.setNext(rs);
        }
    }

    public Node<T> addTail(Node<T> curElem, Node<T> newelem)
    {
        if(curElem == null) {
            System.out.printf("At the tail, Return %s\n",newelem);
            return newelem;
        }
        else {
            System.out.printf("%s.setNext(Addtail(%s,%s))\n",curElem,curElem.getNext(),newelem);
            Node<T> rs = addTail(curElem.getNext(),newelem);
            System.out.printf("%s.setNext(%s)\n",curElem,rs==null?"NULL":rs);
            curElem.setNext(rs);
            System.out.printf("Return %s\n",curElem);
            return curElem;
        }
    }

    public void addTailLoop(T data)
    {
        Node<T> node = new Node<>(data);
        if(head==null)//The list is empty
            head=node;
        else {
            Node<T> cur = head;
            while(cur.getNext()!=null)cur=cur.getNext();
            cur.setNext(node);
        }
    }


}
