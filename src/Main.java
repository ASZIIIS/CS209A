//Java Fast IO
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        QReader input = new QReader();
        QWriter output = new QWriter();
        String string = input.next();
        int length = string.length();
        int length1 = (int)Math.sqrt(length);//根号长度
        int length2;//所有根号长度都被减去后的最后一个的长度
        if(length%length1 == 0){
            length2 = length1;
        }else {//如果除length1为0说明最后一个长度也是length1
            length2 = length%length1;
        }//不然就是余下的长度
        arraylist head = new arraylist(" ");//做一个头便于每次返回头部重新遍历
        arraylist tail = new arraylist("  ");
        arraylist cur = head;//现在的下标指向头
        for(int i = 0;i<length;i+=length1){//用i来裁剪子序列放到每个组里面
            String substring;
            if(length-i+1<=length1){
                substring = string.substring(i);
            }else{
                substring = string.substring(i,i+length1);
            }
            cur.next = new arraylist(substring);
            cur = cur.next;
        }cur.next = tail;
        cur = head.next;
        //连接所有的含有数组的节点完成了，每个数组的长度是根号length
        int NumOfAct = input.nextInt();//共有这些次操作
        for(int i = 0;i<NumOfAct;i++){
            switch (input.nextInt()) {
//操作1================================================================================================================//
                case 1:
                    String x = input.next();
                    char insert = x.charAt(0);//把要插入的字符串变为char类型
                    int position = input.nextInt();//要被插入的位置
                    while(position>cur.now+1){//now是当前的下标，比如有3个，下标为2，则共有now＋1个
                        position-=(cur.now+1);//减去前一个遍历使用的个数
                        cur = cur.next;//不在前一个里面则遍历下一个
                        assert cur != null;
                        if(cur.next == tail && position == 1){//如果到了最后一个，position减完了所有的数都还剩下1，说明插入最后一个
                            cur.arraylist[cur.now+1] = insert;//最后一个下标加上1是新一个的下标
                            cur.now++;//
                        }
                    }//找到了要被插入的cur
                    if(!(cur.next == tail && position == 1)){//如果不是最后一种情况发生
                        for(int y = cur.now;y>=position-1;y--){
                            cur.arraylist[y+1]=cur.arraylist[y];
                        }//插入之后从cur的下标处
                        cur.now++;
                    }
                    if(!cur.istrans)
                        cur.arraylist[position-1] = insert;
                    else{
                        cur.arraylist[position-1] = (char) ('a' + 'z' - insert);
                    }
                    if(cur.now == cur.arraylist.length-1){//如果数组满了
                        String behind = "";
                        for(int y = cur.staticLength;y<=cur.now;y++){
                            behind = behind + cur.arraylist[y];
                        }
                        //output.println("="+behind+"=");
                        arraylist newArr = new arraylist(behind);
                        newArr.next = cur.next;
                        cur.next = newArr;
                        cur.now = cur.nowget;
                        //output.println("!@#$%^&*");
                    }/*output.print("$");
                    for(int j = 0;j<cur.arraylist.length;j++){
                        if(!(cur.arraylist[j] == 0)){
                            output.print(cur.arraylist[j]);
                        }
                    }output.print("$ "+"\n");
                    output.println(cur.now);*/
                    cur = head.next;
                    break;
//操作2==============================================================================================================//
                case 2:
                    int findPosition = input.nextInt();
                    while(findPosition>cur.now+1){//当这个节点中的所有数字不够找时，跳过
                        cur=cur.next;
                        findPosition-=cur.now+1;
                    }findPosition = findPosition-1;
                    if(!cur.istrans) {
                        output.println(cur.arraylist[findPosition]);
                        //output.println(findPosition);
                    }
                    else{
                        output.println((char) ('a' + 'z' - cur.arraylist[findPosition]));
                        //output.println(findPosition);
                    }

                    cur = head.next;
                    break;
//操作3===============================================================================================================//
                case 3:
                    int l = input.nextInt();
                    int r = input.nextInt();
                    //output.println(cur.now);
                    while(l>cur.now+1){
                        l-=cur.now+1;
                        cur = cur.next;
                    }
                    //output.println(cur.now);
                    for(int y = l-1;y<= cur.now;y++){
                        cur.arraylist[y] = (char)('a' + 'z' - cur.arraylist[y]);
                    }arraylist cur_1 = cur;//标记第一个的位置
                    cur = head.next;//开始遍历找r的位置
                    while(r>cur.now+1){
                        r-=cur.now+1;
                        cur = cur.next;
                    }arraylist cur_2 = cur;
                    cur = cur_2;
                    if(cur_1!=cur_2){
                        for(int y = 0;y<=r-1;y++){
                            cur.arraylist[y] = (char)('a' + 'z' - cur.arraylist[y]);
                        }
                    }else {
                        for(int y = r;y<cur.arraylist.length;y++){
                            cur.arraylist[y] = (char)('a' + 'z' - cur.arraylist[y]);
                        }
                    }

                    /*cur = cur_1;
                    output.print(cur.now);
                    output.print("$");
                    for(int j = 0;j<cur.arraylist.length;j++){
                        if(!(cur.arraylist[j] == 0)){
                            output.print(cur.arraylist[j]);
                        }
                    }output.print("$ "+"\n");*/



                    cur = cur_1;
                    if(cur_2!=cur_1){
                        while(cur.next!=cur_2){
                            cur.next.isTrans();
                            cur = cur.next;
                        }
                    }cur = head.next;
                    break;
            }
        }
        output.close();
    }
}
class arraylist{
    char[] arraylist;
    arraylist next;
    int staticLength;
    int now;
    int nowget;
    boolean istrans = false;
    arraylist(String string){
        this.arraylist = new char[2*string.length()];
        for(int i = 0;i<string.length();i++){
            this.arraylist[i] = string.charAt(i);
        }
        now = string.length()-1;//标记了当前数组内最后一个的下标
        nowget = now;
        staticLength = string.length();
    }
    public void isTrans(){
        this.istrans = !this.istrans;
    }

}

//////////////////////////////////////////////////////////////////////////////////////////////////////


class Untitled {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        while (in.hasNext()) {
            int x = in.nextInt();
            out.println(x);
        }
        out.close();
    }
}
class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}
class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}