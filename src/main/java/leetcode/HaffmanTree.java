package leetcode;

/**
 * Created by ShaoBin on 2016/3/17.
 */
//����������
public class HaffmanTree {
    //���Ȩֵ
    static final int MAXVALUE=1000;
    int nodeNum ; //Ҷ�ӽ�����

    public HaffmanTree(int n)
    {
        this.nodeNum = n;
    }

    //������������㷨
    public void haffman(int[] weight,HaffNode[] nodes)//Ȩֵ���飬���нڵ�����
    {
        int n = this.nodeNum;
        //m1,m2,��ʾ��С������Ȩֵ��x1,x2,��ʾ��С����Ȩֵ��Ӧ�ı��,m1��ʾ��С��m2��ʾ��С
        int m1,m2,x1,x2;

        //��ʼ�����еĽ�㣬��Ӧ��n��Ҷ�ӽ��Ĺ�����������2n-1����㡣
        for(int i=0;i < 2*n-1;i++)
        {
            HaffNode temp = new HaffNode();
            //��ʼ��n��Ҷ�ӽ�㣬��������Ľڵ㡣0��1��2��3��Ҷ�ӽڵ�Ҳ������Ľڵ�
            if(i < n)
            {
                temp.weight = weight[i];
            }
            else
            {
                temp.weight = 0;
            }
            temp.parent = 0;
            temp.flag = 0;
            temp.leftChild = -1;
            temp.rightChild = -1;
            nodes[i] = temp;
        }

        for(int i=0;i<n-1;i++){//��ʼ��n-1����Ҷ�ӽ�㣬n-1��ʾҪѭ��n-1�����n-1������
            m1 = m2 = MAXVALUE;
            x1 = x2 =0;
            for(int j=0;j< n+i;j++)//�����n-1����ʱ��ÿ�ζ��Ǵ�0��n+i-1,����flag=0�ģ�flag=1��ʾ�Ѿ����뵽��������
            {   //����2�����ҳ�Ȩֵ��С��2��
                if(nodes[j].weight<m1 && nodes[j].flag==0)//if������else��else if�Ͳ���ȥ�ˡ�
                {
                    //m1,x1��ʼֵΪ��һ��Ԫ�أ����������m1ҪС����m1ָ���С�ģ�ԭ��m1ָ���������m2ָ��
                    //��������m1���m2С����m2ָ�������m1���m2С�ģ�
                    //Ҳ����˵m1ָ����С�ģ�m2ָ���2С�ġ�
                    m2 = m1;
                    x2 = x1;
                    m1 = nodes[j].weight;
                    x1 = j;
                }
                else if(nodes[j].weight<m2 && nodes[j].flag ==0)
                {
                    m2 = nodes[j].weight;
                    x2 = j;
                }
            }
            //��Ȩֵ��С��2����ϳ�һ��2����
            nodes[x1].parent = n+i;
            nodes[x2].parent = n+i;
            nodes[x1].flag = 1;
            nodes[x2].flag = 1;
            nodes[n+i].weight = nodes[x1].weight + nodes[x2].weight;
            nodes[n+i].leftChild = x1;
            nodes[n+i].rightChild = x2;
        }
        /*
        ��ʼ������֮��[1,3,5,7,4,9,16]
        ���룺100��101��11��0
        */
    }

    //�����������㷨
    public void haffmanCode(HaffNode[] nodes,Code[] haffCode)
    {
        int n = this.nodeNum;
        Code code = new Code(n);//4
        int child,parent;

        for(int i=0;i<n; i++)//��ǰ��n������Ľڵ���б���
        {
            code.start = n-1;//3
            code.weight = nodes[i].weight;//1
            child = i;//0
            parent = nodes[child].parent;
            //��Ҷ�ӽڵ������������ɱ��룬��ͼ���ɡ�
            while(parent!=0)
            {
                if(nodes[parent].leftChild == child)
                {
                    code.bit[code.start] = 0;
                }
                else
                {
                    code.bit[code.start] = 1;
                }

                code.start--;
                child = parent;
                parent = nodes[child].parent;
            }

            Code temp = new Code(n);
            for(int j=code.start+1;j < n;j++)
            {
                temp.bit[j] = code.bit[j];
            }
            temp.weight = code.weight;
            temp.start = code.start;
            haffCode[i] = temp;
        }
    }
}




