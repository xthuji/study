package com.client.test.mail;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

public class SpringMailSender {

    // Spring的邮件工具类，实现了MailSender和JavaMailSender接口
    private JavaMailSenderImpl mailSender;
    
    public SpringMailSender() {
	    // 初始化JavaMailSenderImpl，当然推荐在spring配置文件中配置，这里是为了简单
	    mailSender = new JavaMailSenderImpl();
	    // 设置参数
	    mailSender.setHost("smtp.qq.com");
	    mailSender.setUsername("xthuji@qq.com");
	    mailSender.setPassword("");
    }
    
	/**
	 * 简单邮件发送
	 *
	 */
    @Test
	public void simpleSend() {
	    // 构建简单邮件对象，见名知意
	    SimpleMailMessage smm = new SimpleMailMessage();
	    // 设定邮件参数
	    smm.setFrom(mailSender.getUsername());
	    smm.setTo("xthuji@163.com");
	    smm.setSubject("Hello world");
	    smm.setText("Hello world via spring mail sender");
	    // 发送邮件
	    mailSender.send(smm);
	}
        
	/**
	 * 带附件的邮件发送
	 *
	 * @throws MessagingException
	 * @throws javax.mail.MessagingException 
	 */
    @Test
	public void attachedSend() throws MessagingException, javax.mail.MessagingException {
	    //使用JavaMail的MimeMessage，支付更加复杂的邮件格式和内容
	    MimeMessage msg = mailSender.createMimeMessage();
	    //创建MimeMessageHelper对象，处理MimeMessage的辅助类
	    MimeMessageHelper helper = new MimeMessageHelper(msg, true);
	    //使用辅助类MimeMessage设定参数
	    helper.setFrom(mailSender.getUsername());
	    helper.setTo("xthuji@163.com");
	    helper.setSubject("Hello Attachment");
	    helper.setText("This is a mail with attachment");
	    //加载文件资源，作为附件
//	    ClassPathResource file = new ClassPathResource(
//	    		"com\\client\\test\\mail\\SpringMailSender.class");
	    FileSystemResource file = new FileSystemResource(
	            "C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg");
	    //加入附件
	    helper.addAttachment("attachment.jpg", file);
	    //发送邮件
	    mailSender.send(msg);
	}


	/**发送富文本邮件
	 * @throws MessagingException
	 * @throws javax.mail.MessagingException 
	 */
    @Test
	public void richContentSend() throws MessagingException, javax.mail.MessagingException {
	    MimeMessage msg = mailSender.createMimeMessage();
	 
	    MimeMessageHelper helper = new MimeMessageHelper(msg, true);
	 
	    helper.setFrom(mailSender.getUsername());
	    helper.setTo("xthuji@163.com");
	    helper.setSubject("Rich content mail");
	    //第二个参数true，表示text的内容为html，然后注意<img/>标签，src='cid:file'，'cid'是contentId的缩写，'file'是一个标记，需要在后面的代码中调用MimeMessageHelper的addInline方法替代成文件
	    helper.setText(
	            "<body><p>Hello Html Email</p><img src='cid:file'/></body>",
	            true);
	 
	    FileSystemResource file = new FileSystemResource(
	            "C:\\Users\\Public\\Pictures\\Sample Pictures\\Chrysanthemum.jpg");
	    helper.addInline("file", file);
	 
	    mailSender.send(msg);
	}
	
}
