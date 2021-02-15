package tr.producer.bean;

import tr.common.bean.DataIn;
import tr.common.bean.DataOut;
import tr.common.bean.Producer;
import tr.common.util.DateUtil;
import tr.common.util.NumberUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 生产者对象
 */
public class LocalFileProducer implements Producer {
    private DataIn in;
    private DataOut out;

    public void setIn(DataIn in) {
        this.in = in;
    }

    public void setOut(DataOut out) {
        this.out = out;
    }

    public void produce() {
        try {
            List<Contact> contacts = in.read(Contact.class);

            while (true){
                int call1Index = new Random().nextInt(contacts.size());
                int call2Index;
                while (true){
                    call2Index = new Random().nextInt(contacts.size());
                    if(call1Index != call2Index){
                        break;
                    }
                }

                Contact call1 = contacts.get(call1Index);
                Contact call2 = contacts.get(call2Index);

                // 生成随机的通话时间
                String startDate = "20180101000000";
                String endDate = "20190101000000";

                long startTime = DateUtil.parse(startDate, "yyyyMMddHHmmss").getTime();
                long endTime = DateUtil.parse(endDate, "yyyyMMddHHmmss").getTime();

                // 通话时间
                long calltime = startTime + (long)((endTime - startTime) * Math.random());
                // 通话时间字符串
                String callTimeString = DateUtil.format(new Date(calltime), "yyyyMMddHHmmss");

                // 生成随机的通话时长
                String duration = NumberUtil.format(new Random().nextInt(3000), 4);

                // 生成通话记录
                Calllog log = new Calllog(call1.getTel(), call2.getTel(), callTimeString, duration);

                System.out.println(log);
                // 将通话记录刷写到数据文件中
                out.write(log);

                Thread.sleep(500);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void close() throws IOException {
        if ( in != null ) {
            in.close();
        }

        if ( out != null ) {
            out.close();
        }
    }
}
