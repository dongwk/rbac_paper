package com;

import com.app.util.DateUtil;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.*;
import java.text.DecimalFormat;

/**
 * 数据迁移
 * 1、导入数据（汇付）
 * 2、导出数据（新安）
 * 3、解析数据（新安）
 * 4、填充标的，债权数据（数据库）
 * 5、导出签约关系（新安）
 */
public class MigrationDataTest {

    // 本地
    static String DRIVER = "com.mysql.cj.jdbc.Driver";
    static String URL = "jdbc:mysql://192.168.7.234:3306/product_plan?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=utf8";
    static String USERNAME = "uat_api_java";
    static String PASSWORD = "54GDfethurSDFvhyU5634tIi6778FjsB";

    // 线上
//    static String DRIVER = "com.mysql.cj.jdbc.Driver";
//    static String URL = "jdbc:mysql://172.16.10.1:60101/account?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
//    static String USERNAME = "prod_api_java";
//    static String PASSWORD = "y6fEgykzpwY7LsNNc8DONQDWrZSl1dNF";

    static Connection connection;
    static String HUIFU_SOURCE = "D:\\xinan\\huifu\\";
    static String XINAN_REQUEST = "D:\\xinan\\xinan_request\\";
    static String XINAN_RESPONSE = "D:\\xinan\\xinan_response\\";
    static int size = 5000;

    static{
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 汇付用户
     */
    @Test
    public void huifuUser(){
        try {
            CsvReader reader = new CsvReader(HUIFU_SOURCE+"user.csv", ',', Charset.forName("UTF-8"));
            reader.readHeaders();
            while (reader.readRecord()) {
                String[] row = reader.getValues();

                String username       = row[2 ]; // 账户名
                String id_type        = "15"   ; // 证件类型
                String id_no          = row[3 ]   ; // 证件号
                String mobile         = row[4 ]; // 联系人手机号
                String bank_card      = row[5 ]; // 外部账号
                String bank_mobile    = row[4 ]; // 预留手机号
                String init_money     = row[11]; // 初始金额
                String open_bank      = "" ; // 开户银行
                String bank_code      = "" ; // 银行编码
                String clear_num      = "" ; // 清算行号
                String account_role   = row[10]; // 角色
                String third_user_id  = row[1 ]; // 用户号

                PreparedStatement ps = connection.prepareStatement("insert into " +
                        " qy_user(username, id_type, id_no, mobile, bank_card, bank_mobile, init_money, open_bank, bank_code, clear_num, account_role, third_user_id) " +
                        " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                ps.setString(1 ,username       );
                ps.setString(2 ,id_type        );
                ps.setString(3 ,id_no          );
                ps.setString(4 ,mobile         );
                ps.setString(5 ,bank_card      );
                ps.setString(6 ,bank_mobile    );
                ps.setString(7 ,init_money     );
                ps.setString(8 ,open_bank      );
                ps.setString(9 ,bank_code      );
                ps.setString(10,clear_num      );
                ps.setString(11,account_role   );
                ps.setString(12,third_user_id  );
                ps.execute();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新安用户
     */
    @Test
    public void xinanUserRequest(){
        try {
            String  filePath = null;
            HSSFWorkbook wb = null;
            PreparedStatement ps = connection.prepareStatement("select id, username, id_type, id_no, mobile, bank_card, bank_mobile, init_money, open_bank, bank_code, clear_num, account_role from qy_user");
            ResultSet rs = ps.executeQuery();
            int i = 1;
            CsvWriter csvWriter = null;
            boolean bool = rs.next();
            while (bool) {
                // 批次文件
                if (i%size == 1){
                    String name = "12201808280000000004_"+size*(i/size+1);
                    if (csvWriter != null) csvWriter.close();
                    filePath = XINAN_REQUEST+name+".csv";
                    csvWriter = new CsvWriter(filePath, ',', Charset.forName("UTF-8"));
                }else{
                    csvWriter.endRecord();
                }

                String id             = rs.getString(1 );
                String username       = rs.getString(2 );
                String id_type        = rs.getString(3 );
                String id_no          = rs.getString(4 );
                String mobile         = rs.getString(5 );
                String bank_card      = rs.getString(6 );
                String bank_mobile    = rs.getString(7 );
                String init_money     = rs.getString(8 );
                String open_bank      = rs.getString(9 );
                String bank_code      = rs.getString(10);
                String clear_num      = rs.getString(11);
                String account_role   = rs.getString(12);

                // 角色
                account_role = "1".equals(account_role) ? "2":"1";

                String[] csvContent = { id, username, id_type, id_no, mobile, bank_card, bank_mobile, init_money, open_bank, bank_code, clear_num, account_role};
                for (String s : csvContent) {
                    csvWriter.write(s);
                }

                bool = rs.next();
                i++;
            }

            if (csvWriter != null) {
                csvWriter.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新安用户
     */
    @Test
    public void xinanUserResponse(){
        try {
            File f = new File(XINAN_RESPONSE+"/user");
            File[] files = f.listFiles();

            for (File file : files) {
                CsvReader reader = new CsvReader(new FileInputStream(file), ',', Charset.forName("UTF-8"));
                boolean bool = reader.readRecord();
                while (bool) {
                    String[] row = reader.getValues();

                    String id_no        = row[3 ]; // 证件号
                    String status       = row[9 ]; // 账户状态
                    String act_way      = row[10]; // 激活方式
                    String act_money    = row[11]; // 指定来账激活金额
                    String bd_no        = row[12]; // 绑定编号
                    String cust_no      = row[13]; // 客户号
                    String account_no   = row[14]; // 电子账号
                    String open_result  = row[15]; // 开户结果
                    String proc_status  = null;    // 无处理状态

                    PreparedStatement ps = connection.prepareStatement("update qy_user set " +
                            "status=?, act_way=?, act_money=?, bd_no=?, cust_no=?, account_no=?, open_result=?, proc_status=?  " +
                            "where id_no=?");

                    ps.setString(1 ,pro(status      ));
                    ps.setString(2 ,pro(act_way     ));
                    ps.setString(3 ,pro(act_money   ));
                    ps.setString(4 ,pro(bd_no       ));
                    ps.setString(5 ,pro(cust_no     ));
                    ps.setString(6 ,pro(account_no  ));
                    ps.setString(7 ,pro(open_result ));
                    ps.setString(8 ,pro(proc_status ));
                    ps.setString(9 ,pro(id_no       ));
                    System.out.println(id_no+" "+ps.executeUpdate());
                    bool = reader.readRecord();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String pro(String v){
        if (v == null || v.equals("null")){
            return "";
        }
        return v;
    }

    /**
     * 汇付标的
     */
    @Test
    public void huifuBorrow(){
        DecimalFormat df = new DecimalFormat("#0.00000");
        try {
            CsvReader reader = new CsvReader(HUIFU_SOURCE+"borrow.csv", ',', Charset.forName("UTF-8"));
            reader.readHeaders();
            while (reader.readRecord()) {
                String[] row = reader.getValues();

                String borrow_no              = row[2 ]; // 标的号
                String borrow_desc            = ""; // 标的描述
                String borrow_mony            = row[3 ]; // 借款金额
                String pay_way                = "2"; // 付息方式
                String pay_day                = "01"; // 利息每月支付日
                String period                 = ""; // 项目期限
                String yield_rate             = df.format(Double.parseDouble(row[11]));    // 预期年化收益率
                String borrower_account_no    = "";  // 借款人电子账号
                String guarantee_account_no   = "null"; // 担保人电子账号
                String nominal_account_no     = "null"; // 名义借款人电子账号
                String payee_account_no       = "null"; // 收款人电子账户
                String borrow_type            = "1"; // 受托支付标志
                String remark                 = "保留域"; // 保留域
                String third_user_id          = row[10];    // 汇付ID

                PreparedStatement ps = connection.prepareStatement("insert into " +
                        "qy_borrow(borrow_no, borrow_desc, borrow_mony, pay_way, pay_day, period, yield_rate, borrower_account_no, guarantee_account_no, nominal_account_no, payee_account_no, borrow_type, remark, third_user_id) " +
                        "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                ps.setString(1 ,borrow_no             );
                ps.setString(2 ,borrow_desc           );
                ps.setString(3 ,borrow_mony           );
                ps.setString(4 ,pay_way               );
                ps.setString(5 ,pay_day               );
                ps.setString(6 ,period                );
                ps.setString(7 ,yield_rate            );
                ps.setString(8 ,borrower_account_no   );
                ps.setString(9 ,guarantee_account_no  );
                ps.setString(10,nominal_account_no    );
                ps.setString(11,payee_account_no      );
                ps.setString(12,borrow_type           );
                ps.setString(13,remark                );
                ps.execute();
            }

            /*
            // 电子账号 & 项目期限 & 标的描述
            PreparedStatement ps = connection.prepareStatement("update qy_borrow set account_no=(select account_no from qy_user t where t.third_user_id=qy_borrow.third_user_id);");
            ps.executeUpdate();
            ps.close();

            // 标的描述
            ps = connection.prepareStatement("update qy_borrow set borrow_desc=(select borrow_name from product_plan.borrow t where SUBSTR(t.`borrow_no`,5)=qy_borrow.borrow_no);");
            ps.executeUpdate();
            ps.close();

            // 项目期限
            ps = connection.prepareStatement("update qy_borrow set period=(select case when b.period_unit = 1 then b.period_length when b.period_unit = 2 then b.period_length*7 when b.period_unit = 3 then b.period_length*30 when b.period_unit = 4 then b.period_length*365 end from product_plan.borrow b where SUBSTR(b.`borrow_no`,5)=qy_borrow.borrow_no);");
            ps.executeUpdate();
            ps.close();
            */

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新安标的
     */
    @Test
    public void xinanBorrowRequest(){
        try {
            HSSFWorkbook wb = null;
            PreparedStatement ps = connection.prepareStatement("select borrow_no, borrow_desc, borrow_mony, pay_way, pay_day, period, yield_rate, borrower_account_no, guarantee_account_no, nominal_account_no, payee_account_no, borrow_type, remark, third_user_id from qy_borrow");
            ResultSet rs = ps.executeQuery();
            int i = 1;
            CsvWriter csvWriter = null;
            String batch_no = null;
            boolean bool = rs.next();
            while (bool) {
                // 批次文件
                if (i%size == 1){
                    batch_no = size*(i/size+1) + "";
                    String name = "5001-BIDIN-6615354277393583-"+batch_no;
                    if (csvWriter != null) csvWriter.close();
                    csvWriter = new CsvWriter(XINAN_REQUEST+name+".csv", ',', Charset.forName("UTF-8"));
                }else{
                    csvWriter.endRecord();
                }

                String borrow_no              = rs.getString(1 );
                String borrow_desc            = rs.getString(2 );
                String borrow_mony            = rs.getString(3 );
                String pay_way                = rs.getString(4 );
                String pay_day                = rs.getString(5 );
                String period                 = rs.getString(6 );
                String yield_rate             = rs.getString(7 );
                String borrower_account_no    = rs.getString(8 );
                String guarantee_account_no   = rs.getString(9 );
                String nominal_account_no     = rs.getString(10);
                String payee_account_no       = rs.getString(11);
                String borrow_type            = rs.getString(12);
                String remark                 = rs.getString(13);

                String[] csvContent = { batch_no, borrow_no, borrow_desc, borrow_mony, pay_way, pay_day, period, yield_rate, borrower_account_no, guarantee_account_no, nominal_account_no, payee_account_no, borrow_type, remark};
                for (String s : csvContent) {
                    csvWriter.write(s);
                }

                bool = rs.next();
                i++;
            }
            if (csvWriter != null){
                csvWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新安标的
     */
    @Test
    public void xinanBorrowResponse(){
        try {
            File f = new File(XINAN_RESPONSE+"/borrow");
            File[] files = f.listFiles();

            for (File file : files) {
                CsvReader reader = new CsvReader(new FileInputStream(file), ',', Charset.forName("UTF-8"));
                reader.readHeaders();
                boolean bool = reader.readRecord();
                while (bool) {
                    String[] row = reader.getValues();

                    String borrow_no          = row[1 ]; // 标的编号
                    String proc_status        = row[12]; // 处理状态

                    PreparedStatement ps = connection.prepareStatement("update qy_borrow set " +
                            "proc_status=?" +
                            "where borrow_no=?");

                    ps.setString(1 ,proc_status     );
                    ps.setString(2 ,borrow_no       );
                    ps.executeUpdate();
                    bool = reader.readRecord();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 汇付债权
     */
    @Test
    public void huifuDebt(){
        try {
            CsvReader reader = new CsvReader(HUIFU_SOURCE+"debt.csv", ',', Charset.forName("UTF-8"));
            reader.readHeaders();
            while (reader.readRecord()) {
                String[] row = reader.getValues();

                String batch_no                 = ""; // 批次号
                String account_no               = ""; // 债权持有人电子账号
                String order_no                 = row[3  ]; // 申请流水号
                String debt_mony                = row[3 ]; // 当前持有债权金额
                String start_date               = ""; // 起息日
                String pay_way                  = "2"; // 付息方式
                String pay_day                  = "null"; // 利息每月支付日
                String end_date                 = ""; // 产品到期日
                String yield_rate               = ""; // 预期年化收益率
                String currency                 = "156";   // 币种
                String borrow_no                = row[3 ]; // 标的编号
                String remark                   = "保留域"; // 保留域
                String third_user_id            = row[4 ]; // 汇付ID

                PreparedStatement ps = connection.prepareStatement("insert into " +
                        "qy_debt(batch_no, account_no, order_no, debt_mony, start_date, pay_way, pay_day, end_date, yield_rate, currency, borrow_no, remark, third_user_id) " +
                        "values(?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?, ?)");

                ps.setString(1 ,batch_no      );
                ps.setString(2 ,account_no    );
                ps.setString(3 ,order_no      );
                ps.setString(4 ,debt_mony     );
                ps.setString(5 ,start_date    );
                ps.setString(6 ,pay_way       );
                ps.setString(7 ,pay_day       );
                ps.setString(8 ,end_date      );
                ps.setString(9 ,yield_rate    );
                ps.setString(10,currency      );
                ps.setString(11,borrow_no     );
                ps.setString(12,remark        );
                ps.setString(13,third_user_id );
                ps.executeUpdate();
                ps.close();
            }

            /*
            // 电子账号
            PreparedStatement ps = connection.prepareStatement("update qy_debt set account_no=(select account_no from qy_user t where t.third_user_id=qy_debt.third_user_id);");
            ps.executeUpdate();
            ps.close();

            // 起息日
            ps = connection.prepareStatement("update qy_borrow set start_date=(select borrow_name from basic_business.borrow t where SUBSTR(t.`borrow_no`,5)=qy_borrow.borrow_no);");
            ps.executeUpdate();
            ps.close();

            // 产品到期日
            ps = connection.prepareStatement("update qy_borrow set start_date=(select borrow_name from basic_business.borrow t where SUBSTR(t.`borrow_no`,5)=qy_borrow.borrow_no);");
            ps.executeUpdate();
            ps.close();

            // 年化收益率
            ps = connection.prepareStatement("update qy_debt set yield_rate=(select t.yield_rate from qy_borrow t where t.borrow_no=qy_debt.borrow_no);");
            ps.executeUpdate();
            */
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新安债权
     */
    @Test
    public void xinanDebtRequest(){
        try {
            HSSFWorkbook wb = null;
            PreparedStatement ps = connection.prepareStatement("select batch_no, account_no, order_no, debt_mony, start_date, pay_way, pay_day, end_date, yield_rate, currency, borrow_no, remark from qy_debt");
            ResultSet rs = ps.executeQuery();
            int i = 1;
            CsvWriter csvWriter = null;
            String batch_no = null;
            boolean bool = rs.next();
            while (bool) {
                // 批次文件
                if (i%size == 1){
                    batch_no = size*(i/size+1) + "";
                    String name = "5001-BID-6615354277393583-"+batch_no;
                    if (csvWriter != null) csvWriter.close();
                    csvWriter = new CsvWriter(XINAN_REQUEST+name+".csv", ',', Charset.forName("UTF-8"));
                }else{
                    csvWriter.endRecord();
                }

                String account_no       = rs.getString(2 );
                String order_no         = rs.getString(3 );
                String debt_mony        = rs.getString(4 );
                String start_date       = rs.getString(5 );
                String pay_way          = rs.getString(6 );
                String pay_day          = rs.getString(7 );
                String end_date         = rs.getString(8 );
                String yield_rate       = rs.getString(9 );
                String currency         = rs.getString(10);
                String borrow_no        = rs.getString(11);
                String remark           = rs.getString(12);

                String[] csvContent = {  batch_no, account_no, order_no, debt_mony, start_date, pay_way, pay_day, end_date, yield_rate, currency, borrow_no, remark};
                for (String s : csvContent) {
                    csvWriter.write(s);
                }

                bool = rs.next();
                i++;
            }

            if (csvWriter != null) csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 新安债权返回
     */
    @Test
    public void xinanDebtResponse(){
        try {
            File f = new File(XINAN_RESPONSE+"/debt");
            File[] files = f.listFiles();

            for (File file : files) {
                CsvReader reader = new CsvReader(new FileInputStream(file), ',', Charset.forName("UTF-8"));
                reader.readHeaders();
                boolean bool = reader.readRecord();
                while (bool) {
                    String[] row = reader.getValues();

                    String order_no     = row[2]; // 申请流水号
                    String proc_status  = row[8]; // 处理状态
                    String auth_code    = row[5]; // 申请授权码

                    PreparedStatement ps = connection.prepareStatement("update qy_debt set " +
                            " proc_status=?, auth_code=?  " +
                            " where order_no=? ");

                    ps.setString(1 ,proc_status );
                    ps.setString(2 ,auth_code );
                    ps.setString(3 ,order_no );
                    ps.executeUpdate();
                    bool = reader.readRecord();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 签约关系
     */
    @Test
    public void xinanSignRequest(){
        try {
            HSSFWorkbook wb = null;
            PreparedStatement ps = connection.prepareStatement("select account_no, sign_type, order_no, sign_date, sign_time, single_mony, sum_mony, effective_date, expiry_date, remark from qy_sign");
            ResultSet rs = ps.executeQuery();
            int i = 1;
            CsvWriter csvWriter = null;
            String batch_no = null;
            boolean bool = rs.next();
            while (bool) {
                // 批次文件
                if (i%size == 1){
                    batch_no = size*(i/size+1) + "";
                    String name = "5001-BIDIN-6615354277393583-"+batch_no;
                    if (csvWriter != null) csvWriter.close();
                    csvWriter = new CsvWriter(XINAN_REQUEST+name+".csv", ',', Charset.forName("UTF-8"));
                }else{
                    csvWriter.endRecord();
                }

                String account_no       = rs.getString(1 );
                String sign_type        = rs.getString(2 );
                String order_no         = rs.getString(3 );
                String sign_date        = rs.getString(4 );
                String sign_time        = rs.getString(5 );
                String single_mony      = rs.getString(6 );
                String sum_mony         = rs.getString(7 );
                String effective_date   = TUtil.timeStamp(DateUtil.parseDate(rs.getString(8), "yyyy-MM-dd HH:mm:ss"))+"";
                String expiry_date      = TUtil.timeStamp(DateUtil.parseDate(rs.getString(9), "yyyy-MM-dd HH:mm:ss"))+"";
                String remark           = rs.getString(10);

                String[] csvContent = { batch_no, account_no, sign_type, order_no, sign_date, sign_time, single_mony, sum_mony, effective_date, expiry_date, remark};
                for (String s : csvContent) {
                    csvWriter.write(s);
                }

                bool = rs.next();
                i++;
            }

            if (csvWriter != null) csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 签约关系返回
     */
    @Test
    public void xinanSignResponse(){
        try {
            File f = new File(XINAN_RESPONSE+"/sign");
            File[] files = f.listFiles();

            for (File file : files) {
                CsvReader reader = new CsvReader(new FileInputStream(file), ',', Charset.forName("UTF-8"));
                boolean bool = reader.readRecord();
                while (bool) {
                    String[] row = reader.getValues();

                    String account_no   = row[1]; // 电子账号
                    String proc_status  = row[11]; // 处理状态

                    PreparedStatement ps = connection.prepareStatement("update qy_sign set " +
                            " proc_status=?  " +
                            " where account_no=? ");

                    ps.setString(1 ,proc_status );
                    ps.setString(2 ,account_no );
                    ps.executeUpdate();
                    bool = reader.readRecord();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始金额 & 汇付
     */
    @Test
    public void balance() {

    }
}
