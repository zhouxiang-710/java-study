package inteviewQues;

/**
 * @ Author     ：zhouxiang.
 * @ Date       ：Created in 20:58 2021/4/6
 * @ Description：${description}
 * @ Modified By：
 * @Version: $version$
 */
class BinTree {
    String value;
    BinTree left;
    BinTree right;
    BinTree(String value) {
        this.value = value;
    }
    public static String preSerialization(BinTree binTree){
        //用来保存二叉树序列化结果的字符串
        StringBuffer sb = new StringBuffer();
        preSerialization(binTree , sb);
        return sb.toString();
    }

        public static void preSerialization(BinTree binTree ,StringBuffer sb){
            if(binTree == null){
                sb.append("#/");
            }else {
                sb.append(binTree.value + "/");
                preSerialization(binTree.left, sb);
                preSerialization(binTree.right, sb);
            }
        }


    static int i = 0;
    public static BinTree preDeserialization(String str){

        String[] split = str.split("/");
        i = -1;
        return preDeserialization(split);
    }

    private static BinTree preDeserialization(String[] split){
        BinTree binTree;
        if( split[++i].equals("#")){
            binTree = null;
        }else{
            binTree = new BinTree(split[i]);
            binTree.left = preDeserialization( split );
            binTree.right = preDeserialization( split );
        }

        return binTree;
    }
    public static void preOrder(BinTree binTree){
        if(binTree == null)
            return;
        System.out.print(binTree.value + " ");
        preOrder(binTree.left);
        preOrder(binTree.right);
    }

    public static void main(String[] args) {
        String test = "a/b/c/#/d/#/#/e/f/#/#/#/g/#/#/";
        BinTree binTree = preDeserialization(test);
        System.out.println("反序列化");
        preOrder(binTree);
        System.out.println();
        String seq = preSerialization(binTree);
        System.out.println("序列化：");
        System.out.println(seq);
    }
}
