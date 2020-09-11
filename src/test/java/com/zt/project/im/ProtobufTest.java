package com.zt.project.im;

//import com.example.tutorial.AddressBookProtos;
import com.google.protobuf.*;
//import com.zt.project.im.proto.StudentOuterClass.Student;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 16:57 2018/4/1
 */
public class ProtobufTest {
    private static final String TEMP_DIR = "D://";
    public static final String PROTOC_PATH = System.getProperty("user.dir")
            + "/protoc/protoc-3.3.0-windows-x86_64.exe";
//            + "/protoc/protoc.exe";
    private File descFile;

//    @Test
//    public void testProtobuf() throws InvalidProtocolBufferException {
//        Student student = Student.newBuilder()
//                .setName("mike")
//                .setAddress("西安")
//                .setAge(20)
//                .build();
//        byte[] studentByte = student.toByteArray();
//        System.out.println(studentByte.length);
//        Student student1 = Student.parseFrom(studentByte);
//        System.out.println(student1);
//    }
//
//    @Test
//    public void testProtobufDynamic() throws IOException {
//        System.out.println("-======");
//        System.out.printf(this.getClass().getResource("/").getPath());
//        Student.getDescriptor();
//        File file = new File("G:\\workspace\\idea\\realproject\\MyIM\\src\\main\\proto\\AdressBook.proto");
//        if(!file.exists()){
//            System.out.printf("文件不存在！");
//            return;
//        }
//        createDescripFile(file);
////        Descriptors.Descriptor pbDescriptor = null;
////        DescriptorProtos.FileDescriptorSet descriptorSet =
////                DescriptorProtos.FileDescriptorSet
////                        .parseFrom(new FileInputStream("G:\\workspace\\idea\\realproject\\MyIM\\src\\main\\proto\\AdressBook.proto"));
////        DescriptorProtos.FileDescriptorSet.
//    }

    private File createDescripFile(File proto) {
        try {
            Runtime run = Runtime.getRuntime();
            String descFileName = System.currentTimeMillis()
                    + "FastProtoParser.desc";
            String protoPath = proto.getCanonicalPath();
            String protoFPath = proto.getParentFile().getAbsolutePath();

            String cmd = PROTOC_PATH + " -I=" + protoFPath
                    + " --descriptor_set_out=" + TEMP_DIR + descFileName + " "
                    + protoPath;
            System.out.println(cmd);

            // 如果不正常终止, 则生成desc文件失败
            Process p = run.exec(cmd);
            if (p.waitFor() != 0) {
                if (p.exitValue() == 1) {// p.exitValue()==0表示正常结束，1：非正常结束
                    throw new RuntimeException("protoc 编译器报错");
                }
            }

            return new File(TEMP_DIR + descFileName);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;

    }

//    public static void main( String[] args ) {

//        Cinema.Movie.Builder movieBuilder = Cinema.Movie.newBuilder();
//        movieBuilder.setName("The Shining");
//        movieBuilder.setType(Cinema.MovieType.ADULT);
//        movieBuilder.setReleaseTimeStamp(327859200);
//        Student student = Student.newBuilder()
//                .setAge(12)
//                .setAddress("hello")
//                .setName("hello")
//                .build();
//
//
//
//        System.out.println("Dynamic Message Parse by proto file");
//        try {
//            byte[] buffer3 = new byte[student.getSerializedSize()];
//            CodedOutputStream codedOutputStream3 = CodedOutputStream.newInstance(buffer3);
//            try {
//                student.writeTo(codedOutputStream3);
//                System.out.println(buffer3);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            String protocCMD = "protoc --descriptor_set_out=cinema.description ./cinema.proto --proto_path=.";
//            Process process = Runtime.getRuntime().exec(protocCMD);
//            process.waitFor();
//            int exitValue = process.exitValue();
//            if (exitValue != 0) {
//                System.out.println("protoc execute failed");
//                return;
//            }
//            Descriptors.Descriptor pbDescritpor = null;
//            DescriptorProtos.FileDescriptorSet descriptorSet = DescriptorProtos.FileDescriptorSet.parseFrom(new FileInputStream("./cinema.description"));
//            for (DescriptorProtos.FileDescriptorProto fdp : descriptorSet.getFileList()) {
//                Descriptors.FileDescriptor fileDescriptor = Descriptors.FileDescriptor.buildFrom(fdp, new Descriptors.FileDescriptor[]{});
//                for (Descriptors.Descriptor descriptor : fileDescriptor.getMessageTypes()) {
//                    if (descriptor.getName().equals("Movie")) {
//                        System.out.println("Movie descriptor found");
//                        pbDescritpor = descriptor;
//                        break;
//                    }
//                }
//            }
//            if (pbDescritpor == null) {
//                System.out.println("No matched descriptor");
//                return;
//            }
//            DynamicMessage.Builder pbBuilder = DynamicMessage.newBuilder(pbDescritpor);
//
//            Message pbMessage = pbBuilder.mergeFrom(buffer3).build();
//            System.out.println(pbMessage);
//
//        } catch (Exception e) {
//            System.out.println("Exception");
//            e.printStackTrace();
//        }
//    }
}
