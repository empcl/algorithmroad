package interview;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Module Desc:
 *  自定义一个wordCount
 * User: empcl
 * DateTime: 2019/8/16 9:46
 */
public class MySelfWordCount {
    public static void main(String[] args) {

        //用HashMap存放<单词:词频>这样一个映射关系
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        try {
            //读取要处理的文件
            BufferedReader br = new BufferedReader(new FileReader("C:\\empcl\\data\\1.txt"));
            String value;
            while ((value = br.readLine()) != null) {
                String[] split = value.split(",");
                int len = split.length;
                for (int i = 0;i < len;i++) {
                    String t = split[i];
                    if (hashMap.containsKey(t)) {
                        hashMap.put(t,hashMap.get(t) + 1);
                    } else {
                        hashMap.put(t,1);
                    }
                }
            }
            //遍历HashMap,输出结果
            Iterator iterator = hashMap.keySet().iterator();
            while (iterator.hasNext()) {
                String word = (String) iterator.next();
                System.out.println(word + ":\t" + hashMap.get(word));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {}
    }
}
