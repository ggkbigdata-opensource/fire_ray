package org.fire.platform.util.itext;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.rtf.RtfWriter2;

/**
 * IText操作类
 * @author shyh
 *
 */
public class ItextManager {

	private Font font;
	private BaseFont bfChinese;

	public ItextManager() throws Exception {
		// 设置中文字体
//		bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		bfChinese = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
		font = new Font(bfChinese);
		font.setSize(15);
		font.setStyle(FontFactory.HELVETICA);
//		font.setStyle(Font.BOLD);//加粗
		font.setColor(new Color(0,0,0));
		
	}

	public static ItextManager getInstance() throws Exception {
		return new ItextManager();
	}

	public void createRtfContext(List<News> newsList, List<String> imgList, OutputStream out,String type) {
		Document doc = new Document(PageSize.A4, 20, 20, 20, 20);
		try {
			if("word".equals(type)){
				RtfWriter2.getInstance(doc, out);
			}else if("pdf".equals(type)){
				PdfWriter.getInstance(doc, out);
			}
			doc.open();
			News news = null;
			Paragraph title1 = null;
			for (int i = 0; i < newsList.size(); i++) {
				news = newsList.get(i);
				// 标题
				Paragraph title = new Paragraph(news.getTitle(), font);
				title.setAlignment(Element.ALIGN_LEFT);
				doc.add(title);

				// 换行
				title1 = new Paragraph("\n");
				doc.add(title1);

				// 正文
				Paragraph content = new Paragraph(news.getContent(), font);
				content.setAlignment(Element.ALIGN_LEFT);
				doc.add(content);

				// 换行
				title1 = new Paragraph("\n");
				doc.add(title1);

				// 站点
				Paragraph site = new Paragraph(news.getSite(), font);
				content.setAlignment(Element.ALIGN_LEFT);
				doc.add(site);

				// 换行
				title1 = new Paragraph("\n");
				doc.add(title1);

				// 发布时间
				Paragraph publishTime = new Paragraph(news.getPublishTime(), font);
				content.setAlignment(Element.ALIGN_LEFT);
				doc.add(publishTime);

				// 换行
				title1 = new Paragraph("\n");
				doc.add(title1);

			}

			Image img = null;
			for (int j = 0; j < imgList.size(); j++) {
				// 图片
				img = Image.getInstance(imgList.get(j));
				float heigth = img.getHeight();
				float width = img.getWidth();
				int percent = getPercent2(heigth, width);
				img.setAlignment(Image.MIDDLE);
				img.scalePercent(percent + 3);// 表示是原来图像的比例;
				doc.add(img);
			}

			doc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 第一种解决方案 在不改变图片形状的同时，判断，如果h>w，则按h压缩，否则在w>h或w=h的情况下，按宽度压缩
	 * 
	 * @param h
	 * @param w
	 * @return
	 */

	public static int getPercent(float h, float w) {
		int p = 0;
		float p2 = 0.0f;
		if (h > w) {
			p2 = 297 / h * 100;
		} else {
			p2 = 210 / w * 100;
		}
		p = Math.round(p2);
		return p;
	}

	/**
	 * 第二种解决方案，统一按照宽度压缩 这样来的效果是，所有图片的宽度是相等的，自我认为给客户的效果是最好的
	 * 
	 * @param args
	 */
	public static int getPercent2(float h, float w) {
		int p = 0;
		float p2 = 0.0f;
		p2 = 530 / w * 100;
		p = Math.round(p2);
		return p;
	}
}
