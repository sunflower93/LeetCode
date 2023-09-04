package leetcode;

//������������
public class Code {

    int[] bit;  //���������
    int start;  //����Ŀ�ʼ�±�
    int weight; //Ȩֵ
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
        //�����������
        haffTree.haffman(weight, nodes);
        //���ɹ���������
        haffTree.haffmanCode(nodes, codes);

        //��ӡ����������
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
