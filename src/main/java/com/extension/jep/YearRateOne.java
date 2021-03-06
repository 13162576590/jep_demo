package main.java.com.extension.jep;

import com.lz.utils.math.DoubleMathUtils;
import com.singularsys.jep.EvaluationException;
import com.singularsys.jep.functions.UnaryFunction;

import java.util.Map;

public class YearRateOne extends UnaryFunction {
    // Use default constructor: variable number of arguments

    // Ensure at least one argument
//    @Override
//    public boolean checkNumberOfParameters(int n) {
//        return true;
//    }

    public Object eval(Object obj) throws EvaluationException {

//        double amount = asDouble(0,args[0]);
//        double newPrice = asDouble(1,args[1]);
//
//        Integer periods = asInt(2,args[2]);
//        double yearRate = asDouble(3,args[3]);
return 0;
//        return this.getYearRate(amount, newPrice, periods, yearRate);
    }

//    public Object eval(double amount, double newPrice, Integer periods, double yearRate) throws EvaluationException {
////        double newPrice = asDouble(0,args[0]);
////        double amount = asDouble(1,args[2]);
////        Integer periods = asInt(2,args[2]);
////        double yearRate = asDouble(3,args[3]);
//
//        return this.getYearRate(amount, newPrice, periods, yearRate);
//    }

    private Double getYearRate(Double amount, Double newPrice, Integer periods, Double yearRate) {
        double yuegong = getMonth(amount, periods, yearRate);

        double yg = 1d;

        double yh = 1d;
        while (DoubleMathUtils.sub(yg, yuegong) <= 0) {
//			System.out.println("差额：" + (yuegong - yg));
//			double i = yh / 1200;
//			yg = (newPrice * i * Math.pow((1 + i), n)) / (Math.pow((1 + i), n) - 1);// 月供
            yg = getMonth(newPrice, periods, yh);// 月供
            yh = DoubleMathUtils.sum(yh, 0.01);
//			System.out.println("yg : " + yg);
        }
//		System.out.println(yh);

        return yh;
    }

    private Double getMonth(Double amount, Integer periods, Double yearRate) {
        double a = 0, i = 0;
        int n = 0;
        double c1;
        double c2;

        a = amount;// 总贷款
        n = periods;

        i = DoubleMathUtils.div(yearRate - 0.001d, 1200, 4);
        c2 = DoubleMathUtils.div(a * i * Math.pow((1 + i), n), Math.pow((1 + i), n) - 1, 2);
        return c2; // 161102 向下取整到小数点后两位
    }


}