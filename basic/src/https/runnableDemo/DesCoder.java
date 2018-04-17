    package https.runnableDemo;  
      
    /** 
     * Created by kingj on 2014/8/13. 
     */  
    import org.apache.commons.codec.binary.Hex;  
      
    import java.security.Key;  
    import java.security.SecureRandom;  
      
    import javax.crypto.Cipher;  
    import javax.crypto.KeyGenerator;  
    import javax.crypto.SecretKey;  
    import javax.crypto.SecretKeyFactory;  
    import javax.crypto.spec.DESKeySpec;  
      
    /** 
     * DES Coder<br/> 
     * secret key length:   56 bit, default:    56 bit<br/> 
     * mode:    ECB/CBC/PCBC/CTR/CTS/CFB/CFB8 to CFB128/OFB/OBF8 to OFB128<br/> 
     * padding: Nopadding/PKCS5Padding/ISO10126Padding/ 
     * @author Aub 
     * 
     */  
    public class DesCoder {  
      
        /** 
         * ��Կ�㷨 
         */  
        private static final String KEY_ALGORITHM = "DES";  
      
        private static final String DEFAULT_CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";  
    //  private static final String DEFAULT_CIPHER_ALGORITHM = "DES/ECB/ISO10126Padding";  
      
      
        /** 
         * ��ʼ����Կ 
         * 
         * @return byte[] ��Կ 
         * @throws Exception 
         */  
        public static byte[] initSecretKey(SecureRandom random) throws Exception{  
            //��������ָ���㷨��������Կ�� KeyGenerator ����  
            KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);  
            //��ʼ������Կ��������ʹ�����ȷ������Կ��С  
            kg.init(random);  
            //����һ����Կ  
            SecretKey  secretKey = kg.generateKey();  
            return secretKey.getEncoded();  
        }  
      
        /** 
         * ת����Կ 
         * 
         * @param key   ��������Կ 
         * @return Key  ��Կ 
         * @throws Exception 
         */  
        public static Key toKey(byte[] key) throws Exception{  
            //ʵ����DES��Կ����  
            DESKeySpec dks = new DESKeySpec(key);  
            //ʵ������Կ����  
            SecretKeyFactory skf = SecretKeyFactory.getInstance(KEY_ALGORITHM);  
            //������Կ  
            SecretKey  secretKey = skf.generateSecret(dks);  
            return secretKey;  
        }  
      
        /** 
         * ���� 
         * 
         * @param data  ���������� 
         * @param key   ��Կ 
         * @return byte[]   �������� 
         * @throws Exception 
         */  
        public static byte[] encrypt(byte[] data,Key key) throws Exception{  
            return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);  
        }  
      
        /** 
         * ���� 
         * 
         * @param data  ���������� 
         * @param key   ��������Կ 
         * @return byte[]   �������� 
         * @throws Exception 
         */  
        public static byte[] encrypt(byte[] data,byte[] key) throws Exception{  
            return encrypt(data, key,DEFAULT_CIPHER_ALGORITHM);  
        }  
      
      
        /** 
         * ���� 
         * 
         * @param data  ���������� 
         * @param key   ��������Կ 
         * @param cipherAlgorithm   �����㷨/����ģʽ/��䷽ʽ 
         * @return byte[]   �������� 
         * @throws Exception 
         */  
        public static byte[] encrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{  
            //��ԭ��Կ  
            Key k = toKey(key);  
            return encrypt(data, k, cipherAlgorithm);  
        }  
      
        /** 
         * ���� 
         * 
         * @param data  ���������� 
         * @param key   ��Կ 
         * @param cipherAlgorithm   �����㷨/����ģʽ/��䷽ʽ 
         * @return byte[]   �������� 
         * @throws Exception 
         */  
        public static byte[] encrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{  
            //ʵ����  
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);  
            //ʹ����Կ��ʼ��������Ϊ����ģʽ  
            cipher.init(Cipher.ENCRYPT_MODE, key);  
            //ִ�в���  
            return cipher.doFinal(data);  
        }  
      
      
      
        /** 
         * ���� 
         * 
         * @param data  ���������� 
         * @param key   ��������Կ 
         * @return byte[]   �������� 
         * @throws Exception 
         */  
        public static byte[] decrypt(byte[] data,byte[] key) throws Exception{  
            return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);  
        }  
      
        /** 
         * ���� 
         * 
         * @param data  ���������� 
         * @param key   ��Կ 
         * @return byte[]   �������� 
         * @throws Exception 
         */  
        public static byte[] decrypt(byte[] data,Key key) throws Exception{  
            return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);  
        }  
      
        /** 
         * ���� 
         * 
         * @param data  ���������� 
         * @param key   ��������Կ 
         * @param cipherAlgorithm   �����㷨/����ģʽ/��䷽ʽ 
         * @return byte[]   �������� 
         * @throws Exception 
         */  
        public static byte[] decrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{  
            //��ԭ��Կ  
            Key k = toKey(key);  
            return decrypt(data, k, cipherAlgorithm);  
        }  
      
        /** 
         * ���� 
         * 
         * @param data  ���������� 
         * @param key   ��Կ 
         * @param cipherAlgorithm   �����㷨/����ģʽ/��䷽ʽ 
         * @return byte[]   �������� 
         * @throws Exception 
         */  
        public static byte[] decrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{  
            //ʵ����  
            Cipher cipher = Cipher.getInstance(cipherAlgorithm);  
            //ʹ����Կ��ʼ��������Ϊ����ģʽ  
            cipher.init(Cipher.DECRYPT_MODE, key);  
            //ִ�в���  
            return cipher.doFinal(data);  
        }  
      
        private static String  showByteArray(byte[] data){  
            if(null == data){  
                return null;  
            }  
            StringBuilder sb = new StringBuilder("{");  
            for(byte b:data){  
                sb.append(b).append(",");  
            }  
            sb.deleteCharAt(sb.length()-1);  
            sb.append("}");  
            return sb.toString();  
        }  
      
    }  