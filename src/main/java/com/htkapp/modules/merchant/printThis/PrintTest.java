package com.htkapp.modules.merchant.printThis;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct;

public class PrintTest implements Printable {
	private List<BuffetFoodOrderProduct> list;
	private String title;
	private String oderNumeber;
	private Integer state;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOderNumeber() {
		return oderNumeber;
	}

	public void setOderNumeber(String oderNumeber) {
		this.oderNumeber = oderNumeber;
	}

	public List<BuffetFoodOrderProduct> getList() {
		return list;
	}

	public void setList(List<BuffetFoodOrderProduct> list) {
		this.list = list;
	}
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE");

	@Override
	public int print(Graphics gra, PageFormat pf, int pageIndex) throws PrinterException {
	    if (pageIndex > 0) {  
            return NO_SUCH_PAGE;  
        }  
		
	
		//转换成Graphics2D
		Graphics2D g2 = (Graphics2D) gra;

		//设置打印颜色为黑色
		g2.setColor(Color.black);

		//打印起点坐标
		double x = pf.getImageableX();
		double y = pf.getImageableY();
		//设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）
		//Java平台所定义的五种字体系列：Serif、SansSerif、Monospaced、Dialog 和 DialogInput
		Font font = new Font("新宋体", Font.PLAIN, 9);
		g2.setFont(font);//设置字体

		//BasicStroke bs_3=new BasicStroke(0.5f);

		float heigth = font.getSize2D();//获取字体的高度  

		//设置小票的标题标题  
		g2.drawString(title,(float)x+25,(float)y+heigth); 

		float line = 2*heigth; //下一行开始打印的高度
		g2.setFont(new Font("宋体", Font.PLAIN,8));//设置正文字体  
		heigth = font.getSize2D();// 字体高度  

		line+=2;
		//设置订单号  
		g2.drawString("订单号:"+oderNumeber, (float)x+20,(float)y+line);  
		line+=heigth+2; 

		//设置标题  
	if(state==1) {
		g2.drawString("名称",(float)x+20, (float)y+line);  
		g2.drawString("单价",(float)x+60, (float)y+line);  
		g2.drawString("数量",(float)x+90, (float)y+line);  
		g2.drawString("小计",(float)x+120, (float)y+line);  
}else if(state==0) {
			g2.drawString("名称",(float)x+20, (float)y+line); 
			g2.drawString("数量",(float)x+120, (float)y+line);  
		}
		line+=heigth;
		/*
		 * 虚线绘制设置    setStroke(Stroke):为 Graphics2D 上下文设置 Stroke
		 * 由 BasicStroke定义的呈现属性描述了用画笔沿 Shape 的轮廓绘制的某个标记的形状，以及应用在 Shape 路径线段的末端和连接处的装饰。
		 * 这些呈现属性包括： 
		 * width：画笔的宽度，是垂直于画笔轨迹的测量值。  此宽度必须大于或等于 0.0f，0.0f为最细线条。  
		 * end caps：在未封闭子路径和虚线线段的末端应用的一些装饰。如果子路径没有 CLOSE 段，则在同一点上开始和结束的子路径仍被认为是未封闭的。
		 * 关于 CLOSE 段的更多信息，请参阅 SEG_CLOSE。三个不同的装饰是：
		 * CAP_BUTT：无装饰地结束未封闭的子路径和虚线线段。
		 * CAP_ROUND：使用半径等于画笔宽度一半的圆形装饰结束未封闭的子路径和虚线线段。
		 * CAP_SQUARE：使用正方形结束未封闭的子路径和虚线线段，正方形越过线段端点，并延长等于线条宽度一半的距离。 
		 * line joins：在两个路径线段的交汇处，以及使用 SEG_CLOSE 封闭的子路径端点的交汇处应用的装饰。
		 * 三个不同的装饰是：
		 * JOIN_BEVEL：通过直线连接宽体轮廓的外角，将路径线段连接在一起。 
		 * JOIN_MITER：扩展路径线段的外边缘，直到它们连接在一起。 
		 * JOIN_ROUND：通过舍去半径为线长的一半的圆角，将路径线段连接在一起。 
		 * miter limit：对剪裁具有 JOIN_MITER 装饰的线接合点的限制。当斜接长度与笔划宽度的比大于 miterlimit 值时，需要剪裁线接合点。
		 * 斜接长度是斜接的对角线长度，即交汇处的内棱角和外棱角之间的距离。两条线段形成的角度越小，斜接长度就越长，交汇处的角度就越尖锐。
		 * 默认 miterlimit 值为 10.0f，它会使所有小于 11 度的角都被剪裁。剪裁斜接会使线接合点的装饰变成斜角。 必须大于或等于 1.0f。
		 * dash attributes：关于如何通过在不透明和透明部分之间交替形成一个虚线模式的定义。 表示虚线模式的数组  
		 * dash phase - 开始虚线模式的偏移量  
		 */
		//虚线设置
		g2.setStroke(new BasicStroke(1f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,4.0f,new float[]{4.0f},0.0f));  
		//在此图形上下文的坐标系中使用当前颜色在点 (x1, y1) 和 (x2, y2) 之间画一条线。 即绘制虚线
		g2.drawLine((int)x,(int)(y+line),(int)x+158,(int)(y+line));  
		line+=heigth; 
		//设置商品清单
		Integer totalNum = 0;
		Double totalPrice = 0.0;
		if(list!=null && list.size()>0){
	if(state==1) {
			for(BuffetFoodOrderProduct bfop:list){
				Double total=bfop.getPrice()*bfop.getQuantity();
				g2.drawString(bfop.getProductName(),(float)x+15, (float)y+line);  
				g2.drawString(bfop.getPrice().toString(),(float)x+60, (float)y+line);  
				g2.drawString(bfop.getQuantity().toString(),(float)x+95,(float)y+line);  
				g2.drawString(total.toString(),(float)x+120,(float)y+line);  
				line += heigth; 
				totalNum+=bfop.getQuantity();
				totalPrice+=total;
				}
			}else if(state==0) {
				for(BuffetFoodOrderProduct bfop:list){
					g2.drawString(bfop.getProductName(),(float)x+15, (float)y+line);  
					g2.drawString(bfop.getQuantity().toString(),(float)x+120,(float)y+line);  
					line += heigth; 
				}
			}
				if(state==1) {
			g2.drawLine((int) x, (int)(y+line), (int) x + 158, (int)(y+line));  
			line += heigth+2;  
			g2.drawString("商品总数:"+totalNum+ "件",(float)x+15,(float)y+line);  
			g2.drawString("合计:"+totalPrice+" 元", (float)x+80, (float)y+line);  
	}
			line += heigth;  
			line += heigth;
			g2.drawString("时间:"+sdf.format(new Date()),(float)x+15,(float)y+line);  
			line += heigth; 
			//			        if(cardNumber!=null && !"".equals(cardNumber)){
			//        g2.drawString("当前会员:"+cardNumber,(float)x+15,(float)y+line);
			//        line += heigth;
			//        g2.drawString("积分:"+integral,(float)x+15,(float)y+line);  
			//        }
			//        g2.drawString("钱票请当面点清，离开柜台恕不负责",(float)x+15,(float)y+line);  
			//
			//        float[] dash1 = {2.0f};
			//        
			//
			//        //设置打印线的属性。
			//        //1.线宽 2、3、不知道，4、空白的宽度，5、虚线的宽度，6、偏移量
			//        g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, dash1, 0.0f));
			//        //g2.setStroke(bs_3);//设置线宽
			//        float heigth = font.getSize2D();//字体高度
			//        System.out.println("x="+x);
			//        // -1- 用Graphics2D直接输出
			//        //首字符的基线(右下部)位于用户空间中的 (x, y) 位置处
			//        //g2.drawLine(10,10,200,300);
			//
			//        Image src = Toolkit.getDefaultToolkit().getImage("F:\\workspace\\QQ.png");
			//        g2.drawImage(src,(int)x,(int)y,c);
			//        int img_Height=src.getHeight(c);
			//        int img_width=src.getWidth(c);
			//        //System.out.println("img_Height="+img_Height+"img_width="+img_width) ;
			//
			//        g2.drawString(str, (float)x, (float)y+1*heigth+img_Height);
			//        g2.drawLine((int)x,(int)(y+1*heigth+img_Height+10),(int)x+200,(int)(y+1*heigth+img_Height+10));
			//        g2.drawImage(src,(int)x,(int)(y+1*heigth+img_Height+11),c);

		}
		switch(pageIndex){
		case 0:

			return PAGE_EXISTS;

		default:

			return NO_SUCH_PAGE;
		}
	}
}
