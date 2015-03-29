package com.work.test.study;


public class SortingAlgorithms {

	/**
	 * 冒泡排序
	 * 挨个比较，将大的放到最后
	 */
	public static void bubbleSort(int[] obj) {
		checkNone(obj);
		int tmp;
		for (int i = 0; i < obj.length; i++) {
			// 切记，每次都要从第一个开始比。最后的不用再比。
			for (int j = 0; j < obj.length - i - 1; j++) {
				// 对邻接的元素进行比较，如果后面的小，就交换
				if (obj[j]>obj[j + 1]) {
					tmp = obj[j];
					obj[j] = obj[j + 1];
					obj[j + 1] = tmp;
				}
			}
		}
	}
	
	/**
	 * 直接插入排序
	 * 将前面的排好序，然后选择后面的一个元素，将其挨个与前面排好序的序列比较，插在合适的位置
	 */
	public static void insertSort(int[] obj){
		for (int i = 1; i < obj.length; i++) {
			//待插入元素
			int temp = obj[i];
			int j;//前面排好的序列长度
			for (j = i-1; j>=0; j--) {
				//将大于temp的往后移动一位
				if(obj[j]>temp){
					obj[j+1] = obj[j];
				}else{
					break;
				}
			}
			obj[j+1] = temp;
		}
	}

	/**
	 * 选择排序
	 * 循环遍历，找出最小的，放到前面
	 */
	public static void selectSort(int[] obj){
        for (int i = 0; i < obj.length; i++) {
            int min = obj[i];
            int n=i; //最小数的索引
            for(int j=i+1;j<obj.length;j++){
                if(obj[j]<min){  //找出最小的数
                    min = obj[j];
                    n = j;
                }
            }
            obj[n] = obj[i];
            obj[i] = min;
            
        }
	}

	/**
	 * 参数为空检查
	 * @param obj
	 */
	public static void checkNone(int[] obj) {
		if (obj == null || obj.length == 0) {
			try {
				throw new Exception("The argument can not be empty!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		int[] obj={49,38,65,97,76,13,27,49,78,34,12,64,1};
        System.out.println("排序之前：");
        for (int i = 0; i < obj.length; i++) {
            System.out.print(obj[i]+" ");
        }
        
//        bubbleSort(obj);
//        insertSort(obj);
        selectSort(obj);
        
        System.out.println();
        System.out.println("排序之后：");
        for (int i = 0; i < obj.length; i++) {
            System.out.print(obj[i]+" ");
        }
	}
}
