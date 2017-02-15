//哈夫曼编码类
public class Code {

    int[] bit;  //编码的数组
    int start;  //编码的开始下标
    int weight; //权值
    Code(int n){
        bit = new int[n];
        start = n-1;
    }


    public static void main(String[] args) {

        int n = 4;
        int[] weight = {1,3,5,7};
        HaffmanTree haffTree = new HaffmanTree(n);
        HaffNode[] nodes = new HaffNode[2*n-1];
        Code[] codes = new Code[n];
        //构造哈夫曼树
        haffTree.haffman(weight, nodes);
        //生成哈夫曼编码
        haffTree.haffmanCode(nodes, codes);

        //打印哈夫曼编码
        for(int i=0;i<n;i++)
        {
            System.out.print("Weight="+codes[i].weight+" Code=");
            for(int j=codes[i].start+1;j<n;j++)
            {
                System.out.print(codes[i].bit[j]);
            }
            System.out.println();
        }
    }
}
