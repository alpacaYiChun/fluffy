public class C468 {
    public static void main(String[] args) {
        C468 t = new C468();
        t.validIPAddress("192.0.0.1");
    }
    public String validIPAddress(String queryIP) {
        int maohao = 0;
        int dian = 0;
        char[] ar = queryIP.toCharArray();
        for(int i=0;i<ar.length;i++) {
            if(ar[i] == ':') {
                maohao++;
            }
            else if(ar[i] == '.') {
                dian++;
            }
        }
        if(maohao!=0&&dian!=0) {
            return "Neither";
        }
        if(maohao == 0 && dian == 0) {
            return "Neither";
        }
        if(maohao != 0) {
            if(maohao!=7) {
                return "Neither";
            }
            String[] split = queryIP.split(":");
            if(split.length != 8) {
                return "Neither";
            }
            for(int i=0;i<split.length;i++) {
                if(split[i].length()<1||split[i].length()>4) {
                    return "Neither";
                }
            }
            for(int i=0;i<split.length;i++) {
                char[] rr = split[i].toCharArray();
                for(int j=0;j<rr.length;j++) {
                    char c = rr[j];
                    if((c>'9'||c<'0')&&(c>'f'||c<'a')&&(c>'F'||c<'A')) {
                        return "Neither";
                    }
                }

            }
            return "IPv6";
        } else {
            if(dian!=3) {
                return "Neither";
            }
            String[] split=queryIP.split("\\.");
            if(split.length!=4) {
                return "Neither";
            }
            for(int i=0;i<split.length;i++) {
                if(split[i].length() < 1 ||split[i].length() > 3) {
                    return "Neither";
                }
                char[] rr = split[i].toCharArray();
                if(rr[0]=='0' && rr.length > 1) {
                    return "Neither";
                }
                for(int j=0;j<rr.length;j++) {
                    if(rr[j]>'9'||rr[j]<'0') {
                        return "Neither";
                    }
                }
                int parsed = Integer.parseInt(split[i]);
                System.out.println(parsed);
                if(parsed > 255) {
                    return "Neither";
                }
            }
            return "IPv4";
        }
    }
}
