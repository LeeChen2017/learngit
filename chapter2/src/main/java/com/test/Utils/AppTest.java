
package gov.zsoft.license.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import junit.framework.TestCase;
/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase{
	
	
	
	public static void test1() {
		
		try {
			List<String> fileToStringList = Lists.newArrayList();
			StringBuffer sbuffer =new StringBuffer();
			fileToStringList = FileUtils.readLines(new File("/Users/rishin/Desktop/深圳市漏洞扫描/trans.txt"));
			
		
			for(int i = 0;i<fileToStringList.size();i++) {
				String temp = fileToStringList.get(i);
				if(i>1) {
					if(temp.equals(fileToStringList.get(i-1))) {
						continue;
					}
				}
				sbuffer.append("\r\n");
				sbuffer.append(temp);
				
			}
			FileUtils.writeStringToFile(new File("/Users/rishin/Desktop/深圳市漏洞扫描/trans2.txt"), sbuffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public static void test2() {
		
		try {
			List<String> fileToStringList = Lists.newArrayList();
			StringBuffer sbuffer =new StringBuffer();
			fileToStringList = FileUtils.readLines(new File("/Users/rishin/Desktop/深圳市漏洞扫描/pdftoword.txt"));
			String regex = "行[0-9]{1,4}[\\u3000|\\u0020|\\u00A0][0-9]*";
			for(int i = 0;i<fileToStringList.size();i++) {
				String temp = fileToStringList.get(i);
				if(temp.startsWith("PAGE")) {
					continue;
				}
				//System.out.println(temp+"........................");
				Pattern pattern = Pattern.compile(regex);
				Matcher matcher = pattern.matcher(temp);
				if(matcher.matches() && i > 1 ) {
					//System.out.println(fileToStringList.get(i-1));
					//System.out.println(temp);
					//sbuffer.append(fileToStringList.get(i-1));
					String subStr = fileToStringList.get(i-1);
					
					if(!subStr.contains("/") && !subStr.contains("PAGE")) {
						subStr = fileToStringList.get(i-2)+subStr;
					}
					
					
					if(subStr.startsWith("PAGE")) {
						subStr = fileToStringList.get(i-2);
					}
					if(subStr.contains("/")) {
						subStr = subStr.substring(subStr.indexOf("/")+1, subStr.length());
					}
					sbuffer.append(subStr);
					sbuffer.append("\r\n");
					sbuffer.append(temp);
					sbuffer.append("\r\n");
				}
				
				
			}
			FileUtils.writeStringToFile(new File("/Users/rishin/Desktop/深圳市漏洞扫描/trans3.txt"), sbuffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static  void test3() {
		try {
			StringBuffer sbuffer = new StringBuffer();
			List<String> fileToStringList = Lists.newArrayList();
			fileToStringList = FileUtils.readLines(new File("/Users/rishin/Desktop/深圳市漏洞扫描/trans3.txt"));
			String fileName = "";
			for(int i = 0;i<fileToStringList.size() ;i++) {
				String temp = fileToStringList.get(i);
				//System.out.println(temp);
				//获取文件名
				if(temp.endsWith(".java") && temp.contains(" ")) {
					String [] fileNameSplit  = temp.split(" ");
					if(fileNameSplit !=null && fileNameSplit.length > 1) {
						String subStr = fileNameSplit[1];
						if(subStr.contains("/")) {
							subStr =subStr.substring(subStr.indexOf("/")+1, subStr.length());
						}
						fileName = subStr;
					}
				} else if(temp.endsWith(".java") ) {
					if(temp.contains("/")) {
						temp = temp.substring(temp.indexOf("/")+1, temp.length());
					}
					fileName = temp;
				}
				if(i == fileToStringList.size()-1) {
					break;
				}
				String subStr2 = fileToStringList.get(i+1);
				Integer lineNumber = 0;
				if(subStr2.startsWith("行")) {
					lineNumber = Integer.valueOf(subStr2.split(" ")[1]);
					sbuffer.append(lineNumber + " "+fileName+"\r\n");
				}
				
			}
			FileUtils.writeStringToFile(new File("/Users/rishin/Desktop/深圳市漏洞扫描/trans5.txt"), sbuffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test4() {
		
		try {
			List<String> fileToStringList = Lists.newArrayList();
			fileToStringList = FileUtils.readLines(new File("/Users/rishin/Desktop/深圳市漏洞扫描/trans5.txt"));
			
			Map<String , List<Integer>>  fileMap= Maps.newHashMap();
			for(int i = 0;i<fileToStringList.size();i++) {
				Integer lineNumber = 0;
				String locatFileName = "";
				String [] currentDate = fileToStringList.get(i).split(" ");
				lineNumber = Integer.valueOf(currentDate[0]);
				locatFileName = currentDate[1];
				if(fileMap.get(locatFileName)==null) {
					fileMap.put(locatFileName, Lists.newArrayList(lineNumber));
				}else {
					fileMap.get(locatFileName).add(lineNumber);
				}
				
			}
			for(String fileName:fileMap.keySet()) {
				folderMethod2("/Users/rishin/Desktop/深圳市漏洞扫描/深圳市公安部门版电子证照系统_20200219_发出版", fileName, fileMap.get(fileName));
			}
			
			//folderMethod2("/Users/rishin/Desktop/深圳市漏洞扫描/深圳市公安部门版电子证照系统_20200219_发出版", locatFileName, lineNumber);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	public static void folderMethod2(String path , String locatFileName , List<Integer> lines ) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        folderMethod2(file2.getAbsolutePath() , locatFileName , lines);
                    } else {
                    	System.out.println("文件："+file2.getName());
                       //获取文件行数，并替换文本
                    	//readAppointedLineNumber(file2 , line);
                    	if(file2.getName().equals(locatFileName)) {
                    		//int modifyLine=lineNumber;//要修改的行
                    		String parent = file2.getParent();
                    		//System.out.println(parent);
                            BufferedReader in_=new BufferedReader(new FileReader(file2.getAbsolutePath()));
                            //String redowLocationPre = "/Users/rishin/Desktop/深圳市漏洞扫描/深圳市公安部门版电子证照系统_20200219_发出版2/";
                            //String rewriteLocation = redowLocationPre+locatFileName;
                            if(parent.contains("/Users/rishin/Desktop/深圳市漏洞扫描/深圳市公安部门版电子证照系统_20200219_发出版")) {
                            	parent = parent.replace("/Users/rishin/Desktop/深圳市漏洞扫描/深圳市公安部门版电子证照系统_20200219_发出版", "/Users/rishin/Desktop/深圳市漏洞扫描/深圳市公安部门版电子证照系统_20200219_发出版2");
                            }
                            File writeFilePath = new File(parent);
                            if(!writeFilePath.exists()) {
                            	writeFilePath.mkdirs();
                            }
                            File filewrite = new File(parent+"/"+locatFileName);
                            if(!filewrite.exists()) {
                            	filewrite.createNewFile();
                            }
                            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(parent+"/"+locatFileName)));
                            String line;
                            int count=1;
                            while((line=in_.readLine())!=null){
                                if(lines.contains(count)){
                                    out.println("//被替换location_number");  //处理就是替换w成Z
                                }else{
                                    out.println(line);
                                }
                                count++;
                            }
                            in_.close();
                            out.close();
                    	}
                    	
                    	
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
	
	
	// 读取文件指定行。
		static void readAppointedLineNumber(File sourceFile, int lineNumber) throws IOException {
			FileReader in = new FileReader(sourceFile);
			LineNumberReader reader = new LineNumberReader(in);
			String s = null;
			int line = 1;
			if (lineNumber < 0 || lineNumber > getTotalLines(sourceFile)) {
				System.out.println("不在文件的行数范围之内。");
			} else {
	 
				System.out.println("当前行号为:" + reader.getLineNumber());
	 
				reader.setLineNumber(23);
				System.out.println("更改后行号为:" + reader.getLineNumber());
				long i = reader.getLineNumber();
				while (reader.readLine() != null) {
					line++;
					if (i == line) {
						s = reader.readLine();
						System.out.println(s);
						break;
					}
	 
				}
	 
			}
	 
			reader.close();
			in.close();
	 
		}
		
		public static void test5() {
			File file = new File("/Users/rishin/Desktop/深圳市漏洞扫描/trans6.txt");
			System.out.println(file.getParent());
		}
		

		// 文件内容的总行数。
		static int getTotalLines(File file) throws IOException {
			FileReader in = new FileReader(file);
			LineNumberReader reader = new LineNumberReader(in);
			String s = reader.readLine();
			int lines = 0;
			while (s != null) {
				lines++;
				s = reader.readLine();
			}
			reader.close();
			in.close();
			return lines;
		}
	
	public static void main(String[] args) {
		
//		String demo = "行154 162" ;
//		String regex = "行[0-9]{1,4}[\\u3000|\\u0020|\\u00A0][0-9]*";
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(demo);
//		System.out.println(matcher.matches());
		//test3();
		
		test4();
		
//		try {
//			String fileToString = FileUtils.readFileToString(new File("/Users/rishin/Desktop/深圳市漏洞扫描/pdftoword.txt"));
//			//System.out.println(fileToString);
//			//FileUtils.read
//			List<String> fileToStringList = Lists.newArrayList();
//			StringBuffer sbuffer =new StringBuffer();
//			fileToStringList = FileUtils.readLines(new File("/Users/rishin/Desktop/深圳市漏洞扫描/pdftoword.txt"));
//			for(int i = 0;i<fileToStringList.size();i++) {
//				String str = fileToStringList.get(i);
//				if(str.indexOf(".java. 文件")!=-1) {
//					sbuffer.append(str);
//					sbuffer.append("\r\n");
//					sbuffer.append(fileToStringList.get(i+1));
//					
//				}
//			}
//			FileUtils.writeStringToFile(new File("/Users/rishin/Desktop/深圳市漏洞扫描/trans.txt"), sbuffer.toString());
//			
//			
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
}
