package com.zt.project.im;

/**
 * @Author：ZhangTao
 * @Description:
 * @Date: Created in 20:48 2018/4/7
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.protobuf.DescriptorProtos.FileDescriptorProto;
import com.google.protobuf.DescriptorProtos.FileDescriptorSet;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.DescriptorValidationException;
import com.google.protobuf.Descriptors.FieldDescriptor;
import com.google.protobuf.Descriptors.FileDescriptor;
import com.google.protobuf.DynamicMessage;
import com.google.protobuf.DynamicMessage.Builder;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;

public class ProtoParserOrBuilder {
    private Map<String, Descriptor> descriptors = null;
    private static final String TEMP_DIR = "D://";
    public static final String PROTOC_PATH = System.getProperty("user.dir")
            + "/protoc/protoc.exe";
    private File descFile;

    public ProtoParserOrBuilder() {
        descriptors = new HashMap<String, Descriptor>();
    }

    public ProtoParserOrBuilder(File proto) {
        descriptors = new HashMap<String, Descriptor>();

        init(proto);
    }

    private void init(File proto) {
        if (descFile != null && descFile.exists()) {
            descFile.delete();
        }
        this.descFile = createDescripFile(proto);

        FileInputStream fin = null;
        try {
            fin = new FileInputStream(descFile);

            FileDescriptorSet descriptorSet = FileDescriptorSet.parseFrom(fin);

            for (FileDescriptorProto fdp : descriptorSet.getFileList()) {
                FileDescriptor fd = FileDescriptor.buildFrom(fdp,
                        new FileDescriptor[] {});

                for (Descriptor descriptor : fd.getMessageTypes()) {
                    String className = descriptor.getName();

                    this.descriptors.put(className, descriptor);
                }
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DescriptorValidationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

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

    public <T> T parse(Class<T> clazz, byte[] bytes) {
        String className = clazz.getSimpleName();
        Descriptor desc = this.descriptors.get(className);

        Map<String, String> fields = new HashMap<String, String>();
        try {
            DynamicMessage message = DynamicMessage.parseFrom(desc, bytes);
            Map<FieldDescriptor, Object> fieldDescs = message.getAllFields();
            for (Map.Entry<FieldDescriptor, Object> entry : fieldDescs
                    .entrySet()) {
                fields.put(entry.getKey().getName(), entry.getValue()
                        .toString());
            }

            T instance = clazz.newInstance();

//            List<Field> fieldList = ReflectionUtil.gatherAllFields(clazz);
            List<Field> fieldList = null;
            for (Field f : fieldList) {
//                ReflectionUtil.fillField(fields, instance, f);
            }
            return instance;
        } catch (InvalidProtocolBufferException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }

    public byte[] build(Object obj) {

        Class<? extends Object> clazz = obj.getClass();
        String className = clazz.getSimpleName();

        Descriptor desc = this.descriptors.get(className);

        Builder builder = DynamicMessage.newBuilder(desc);
        List<FieldDescriptor> fieldDescs = desc.getFields();
//        List<Field> fields = gatherAllFields(clazz);
        List<Field> fields = null;
        try {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValueObject;
                fieldValueObject = field.get(obj);
                if (fieldValueObject != null) {
                    fieldValues.put(fieldName, fieldValueObject);
                }
            }

            for (FieldDescriptor fieldDesc : fieldDescs) {
                String fieldName = fieldDesc.getName();
                Object val = fieldValues.get(fieldName);
                if (val != null) {
                    builder.setField(fieldDesc, val);
                }
            }

            Message message = builder.build();

            return message.toByteArray();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        this.descFile.delete();
    }

}