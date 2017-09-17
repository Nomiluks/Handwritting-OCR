package img.vector.android;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageToVectorActivity extends Activity {
	int location = 0;
	boolean open = true;
	static int value = 2;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ImageView img  = (ImageView)findViewById(R.id.imageView1);
        ImageView img2 = (ImageView)findViewById(R.id.imageView2);
        int rewidth = 14;
        int reheight= 15;
        Bitmap bmp;
        Bitmap bm[]=new Bitmap[124];
        
        
        bm[0]= BitmapFactory.decodeResource(getResources(), R.drawable.acapn1);
        bm[1]= BitmapFactory.decodeResource(getResources(), R.drawable.bcapn1);
        bm[2]= BitmapFactory.decodeResource(getResources(), R.drawable.ccapn1);
        bm[3]= BitmapFactory.decodeResource(getResources(), R.drawable.dcapn1);
        bm[4]= BitmapFactory.decodeResource(getResources(), R.drawable.ecapn1);
        //bm[5]= BitmapFactory.decodeResource(getResources(), R.drawable.ecapn1a);
        //bm[6]= BitmapFactory.decodeResource(getResources(), R.drawable.ecapn1b);
        bm[5]= BitmapFactory.decodeResource(getResources(), R.drawable.fcapn1);
        bm[6]= BitmapFactory.decodeResource(getResources(), R.drawable.gcapn1);
        bm[7]= BitmapFactory.decodeResource(getResources(), R.drawable.hcapn1);
        //bm[10]= BitmapFactory.decodeResource(getResources(), R.drawable.hcapn1a);
        bm[8]= BitmapFactory.decodeResource(getResources(), R.drawable.icapn1);
        bm[9]= BitmapFactory.decodeResource(getResources(), R.drawable.jcapn1);
        bm[10]= BitmapFactory.decodeResource(getResources(), R.drawable.kcapn1);
        bm[11]= BitmapFactory.decodeResource(getResources(), R.drawable.lcapn1);
        bm[12]= BitmapFactory.decodeResource(getResources(), R.drawable.mcapn1);
        bm[13]= BitmapFactory.decodeResource(getResources(), R.drawable.ncapn1);
        bm[14]= BitmapFactory.decodeResource(getResources(), R.drawable.ocapn1);
        //bm18]= BitmapFactory.decodeResource(getResources(), R.drawable.ocapn1a);
        //bm[19]= BitmapFactory.decodeResource(getResources(), R.drawable.ocapn1b);
        //bm[20]= BitmapFactory.decodeResource(getResources(), R.drawable.ocapn1c);
        bm[15]= BitmapFactory.decodeResource(getResources(), R.drawable.pcapn1);
        bm[16]= BitmapFactory.decodeResource(getResources(), R.drawable.qcapn1);
        bm[17]= BitmapFactory.decodeResource(getResources(), R.drawable.rcapn1);
        //bm[24]= BitmapFactory.decodeResource(getResources(), R.drawable.rcapn1a);
        bm[18]= BitmapFactory.decodeResource(getResources(), R.drawable.scapn1);
        bm[19]= BitmapFactory.decodeResource(getResources(), R.drawable.tcapn1);
        //bm[27]= BitmapFactory.decodeResource(getResources(), R.drawable.tcapn1a);
        bm[20]= BitmapFactory.decodeResource(getResources(), R.drawable.ucapn1);
        //bm[29]= BitmapFactory.decodeResource(getResources(), R.drawable.ucapn1a);
        bm[21]= BitmapFactory.decodeResource(getResources(), R.drawable.vcapn1);
        bm[22]= BitmapFactory.decodeResource(getResources(), R.drawable.wcapn1);
        bm[23]= BitmapFactory.decodeResource(getResources(), R.drawable.xcapn1);
        bm[24]= BitmapFactory.decodeResource(getResources(), R.drawable.ycapn1);
        bm[25]= BitmapFactory.decodeResource(getResources(), R.drawable.zcapn1);
        
        bm[26]= BitmapFactory.decodeResource(getResources(), R.drawable.ap1n);
        bm[27]= BitmapFactory.decodeResource(getResources(), R.drawable.bp1n);
        bm[28]= BitmapFactory.decodeResource(getResources(), R.drawable.cp1n);
        bm[29]= BitmapFactory.decodeResource(getResources(), R.drawable.dp1n);
        bm[30]= BitmapFactory.decodeResource(getResources(), R.drawable.ep1n);
        //bm[40]= BitmapFactory.decodeResource(getResources(), R.drawable.ep1na);
        //bm[41]= BitmapFactory.decodeResource(getResources(), R.drawable.ep1nb);
        bm[31]= BitmapFactory.decodeResource(getResources(), R.drawable.fp1n);
        bm[32]= BitmapFactory.decodeResource(getResources(), R.drawable.gp1n);
        bm[33]= BitmapFactory.decodeResource(getResources(), R.drawable.hp1n);
        //bm[45]= BitmapFactory.decodeResource(getResources(), R.drawable.hp1na);
        bm[34]= BitmapFactory.decodeResource(getResources(), R.drawable.ip1n);
        bm[35]= BitmapFactory.decodeResource(getResources(), R.drawable.jp1n);
        bm[36]= BitmapFactory.decodeResource(getResources(), R.drawable.kp1n);
        bm[37]= BitmapFactory.decodeResource(getResources(), R.drawable.lp1n);
        bm[38]= BitmapFactory.decodeResource(getResources(), R.drawable.mp1n);
        bm[39]= BitmapFactory.decodeResource(getResources(), R.drawable.np1n);
        bm[40]= BitmapFactory.decodeResource(getResources(), R.drawable.op1n);
        //bm[53]= BitmapFactory.decodeResource(getResources(), R.drawable.op1na);
        //bm[54]= BitmapFactory.decodeResource(getResources(), R.drawable.op1nb);
        //bm[55]= BitmapFactory.decodeResource(getResources(), R.drawable.op1nc);
        bm[41]= BitmapFactory.decodeResource(getResources(), R.drawable.pp1n);
        bm[42]= BitmapFactory.decodeResource(getResources(), R.drawable.qp1n);
        bm[43]= BitmapFactory.decodeResource(getResources(), R.drawable.rp1n);
        //bm[59]= BitmapFactory.decodeResource(getResources(), R.drawable.rp1na);
        bm[44]= BitmapFactory.decodeResource(getResources(), R.drawable.sp1n);
        bm[45]= BitmapFactory.decodeResource(getResources(), R.drawable.tp1n);
        //bm[62]= BitmapFactory.decodeResource(getResources(), R.drawable.tp1na);
        bm[46]= BitmapFactory.decodeResource(getResources(), R.drawable.up1n);
        //bm[64]= BitmapFactory.decodeResource(getResources(), R.drawable.up1na);
        bm[47]= BitmapFactory.decodeResource(getResources(), R.drawable.vp1n);
        bm[48]= BitmapFactory.decodeResource(getResources(), R.drawable.wp1n);
        bm[49]= BitmapFactory.decodeResource(getResources(), R.drawable.xp1n);
        bm[50]= BitmapFactory.decodeResource(getResources(), R.drawable.yp1n);
        bm[51]= BitmapFactory.decodeResource(getResources(), R.drawable.zp1n);
        
        bm[52]= BitmapFactory.decodeResource(getResources(), R.drawable.zeropn1);
        bm[53]= BitmapFactory.decodeResource(getResources(), R.drawable.onepn1);
        bm[54]= BitmapFactory.decodeResource(getResources(), R.drawable.twopn1);
        bm[55]= BitmapFactory.decodeResource(getResources(), R.drawable.threepn1);
        bm[56]= BitmapFactory.decodeResource(getResources(), R.drawable.fourpn1);
        bm[57]= BitmapFactory.decodeResource(getResources(), R.drawable.fivepn1);
        bm[58]= BitmapFactory.decodeResource(getResources(), R.drawable.sixpn1);
        bm[59]= BitmapFactory.decodeResource(getResources(), R.drawable.sevenpn1);
        bm[60]= BitmapFactory.decodeResource(getResources(), R.drawable.eightpn1);
        bm[61]= BitmapFactory.decodeResource(getResources(), R.drawable.ninepn1);
       
        
        bm[62]= BitmapFactory.decodeResource(getResources(), R.drawable.ap1capz);
        bm[63]= BitmapFactory.decodeResource(getResources(), R.drawable.bp1capz);
        bm[64]= BitmapFactory.decodeResource(getResources(), R.drawable.cp1capz);
        bm[65]= BitmapFactory.decodeResource(getResources(), R.drawable.dp1capz);
        bm[66]= BitmapFactory.decodeResource(getResources(), R.drawable.ep1capz);
        //bm[85]= BitmapFactory.decodeResource(getResources(), R.drawable.e2p1z);
        //bm[86]= BitmapFactory.decodeResource(getResources(), R.drawable.e3p1z);
        bm[67]= BitmapFactory.decodeResource(getResources(), R.drawable.fp1capz);
        bm[68]= BitmapFactory.decodeResource(getResources(), R.drawable.gp1capz);
        bm[69]= BitmapFactory.decodeResource(getResources(), R.drawable.hp1capz);
        //bm[90]= BitmapFactory.decodeResource(getResources(), R.drawable.h2p1z);
        bm[70]= BitmapFactory.decodeResource(getResources(), R.drawable.ip1capz);
        bm[71]= BitmapFactory.decodeResource(getResources(), R.drawable.jp1capz);
        bm[72]= BitmapFactory.decodeResource(getResources(), R.drawable.kp1capz);
        bm[73]= BitmapFactory.decodeResource(getResources(), R.drawable.lp1capz);
        bm[74]= BitmapFactory.decodeResource(getResources(), R.drawable.mp1capz);
        bm[75]= BitmapFactory.decodeResource(getResources(), R.drawable.np1capz);
        bm[76]= BitmapFactory.decodeResource(getResources(), R.drawable.op1capz);
        //bm[98]= BitmapFactory.decodeResource(getResources(), R.drawable.o2p1z);
        //bm[99]= BitmapFactory.decodeResource(getResources(), R.drawable.o3p1z);
        //bm[100]= BitmapFactory.decodeResource(getResources(), R.drawable.o4p1z);
        bm[77]= BitmapFactory.decodeResource(getResources(), R.drawable.pp1capz);
        bm[78]= BitmapFactory.decodeResource(getResources(), R.drawable.qp1capz);
        bm[79]= BitmapFactory.decodeResource(getResources(), R.drawable.rp1capz);
        //bm[104]= BitmapFactory.decodeResource(getResources(), R.drawable.r2p1z);
        bm[80]= BitmapFactory.decodeResource(getResources(), R.drawable.sp1capz);
        bm[81]= BitmapFactory.decodeResource(getResources(), R.drawable.tp1capz);
        //bm[107]= BitmapFactory.decodeResource(getResources(), R.drawable.t2p1z);
        bm[82]= BitmapFactory.decodeResource(getResources(), R.drawable.up1capz);
        //bm[109]= BitmapFactory.decodeResource(getResources(), R.drawable.u2p1z);
        bm[83]= BitmapFactory.decodeResource(getResources(), R.drawable.vp1capz);
        bm[84]= BitmapFactory.decodeResource(getResources(), R.drawable.wp1capz);
        bm[85]= BitmapFactory.decodeResource(getResources(), R.drawable.xp1capz);
        bm[86]= BitmapFactory.decodeResource(getResources(), R.drawable.yp1capz);
        bm[87]= BitmapFactory.decodeResource(getResources(), R.drawable.zp1capz);
        
        bm[88]= BitmapFactory.decodeResource(getResources(), R.drawable.ap1z);
        bm[89]= BitmapFactory.decodeResource(getResources(), R.drawable.bp1z);
        bm[90]= BitmapFactory.decodeResource(getResources(), R.drawable.cp1z);
        bm[91]= BitmapFactory.decodeResource(getResources(), R.drawable.dp1z);
        bm[92]= BitmapFactory.decodeResource(getResources(), R.drawable.ep1z);
        //bm[85]= BitmapFactory.decodeResource(getResources(), R.drawable.e2p1z);
        //bm[86]= BitmapFactory.decodeResource(getResources(), R.drawable.e3p1z);
        bm[93]= BitmapFactory.decodeResource(getResources(), R.drawable.fp1z);
        bm[94]= BitmapFactory.decodeResource(getResources(), R.drawable.gp1z);
        bm[95]= BitmapFactory.decodeResource(getResources(), R.drawable.hp1z);
        //bm[90]= BitmapFactory.decodeResource(getResources(), R.drawable.h2p1z);
        bm[96]= BitmapFactory.decodeResource(getResources(), R.drawable.ip1z);
        bm[97]= BitmapFactory.decodeResource(getResources(), R.drawable.jp1z);
        bm[98]= BitmapFactory.decodeResource(getResources(), R.drawable.kp1z);
        bm[99]= BitmapFactory.decodeResource(getResources(), R.drawable.lp1z);
        bm[100]= BitmapFactory.decodeResource(getResources(), R.drawable.mp1z);
        bm[101]= BitmapFactory.decodeResource(getResources(), R.drawable.np1z);
        bm[102]= BitmapFactory.decodeResource(getResources(), R.drawable.op1z);
        //bm[98]= BitmapFactory.decodeResource(getResources(), R.drawable.o2p1z);
        //bm[99]= BitmapFactory.decodeResource(getResources(), R.drawable.o3p1z);
        //bm[100]= BitmapFactory.decodeResource(getResources(), R.drawable.o4p1z);
        bm[103]= BitmapFactory.decodeResource(getResources(), R.drawable.pp1z);
        bm[104]= BitmapFactory.decodeResource(getResources(), R.drawable.qp1z);
        bm[105]= BitmapFactory.decodeResource(getResources(), R.drawable.rp1z);
        //bm[104]= BitmapFactory.decodeResource(getResources(), R.drawable.r2p1z);
        bm[106]= BitmapFactory.decodeResource(getResources(), R.drawable.sp1z);
        bm[107]= BitmapFactory.decodeResource(getResources(), R.drawable.tp1z);
        //bm[107]= BitmapFactory.decodeResource(getResources(), R.drawable.t2p1z);
        bm[108]= BitmapFactory.decodeResource(getResources(), R.drawable.up1z);
        //bm[109]= BitmapFactory.decodeResource(getResources(), R.drawable.u2p1z);
        bm[109]= BitmapFactory.decodeResource(getResources(), R.drawable.vp1z);
        bm[110]= BitmapFactory.decodeResource(getResources(), R.drawable.wp1z);
        bm[111]= BitmapFactory.decodeResource(getResources(), R.drawable.xp1z);
        bm[112]= BitmapFactory.decodeResource(getResources(), R.drawable.yp1z);
        bm[113]= BitmapFactory.decodeResource(getResources(), R.drawable.zp1z);
        
        bm[114]= BitmapFactory.decodeResource(getResources(), R.drawable.zerop1z);
        bm[115]= BitmapFactory.decodeResource(getResources(), R.drawable.onep1z);
        bm[116]= BitmapFactory.decodeResource(getResources(), R.drawable.twop1z);
        bm[117]= BitmapFactory.decodeResource(getResources(), R.drawable.threep1z);
        bm[118]= BitmapFactory.decodeResource(getResources(), R.drawable.fourp1z);
        bm[119]= BitmapFactory.decodeResource(getResources(), R.drawable.fivep1z);
        bm[120]= BitmapFactory.decodeResource(getResources(), R.drawable.sixp1z);
        bm[121]= BitmapFactory.decodeResource(getResources(), R.drawable.sevenp1z);
        bm[122]= BitmapFactory.decodeResource(getResources(), R.drawable.eightp1z);
        bm[123]= BitmapFactory.decodeResource(getResources(), R.drawable.ninep1z);
        /*bm[0]= BitmapFactory.decodeResource(getResources(), R.drawable.acapusman);
        bm[1]= BitmapFactory.decodeResource(getResources(), R.drawable.bcapusman);
        bm[2]= BitmapFactory.decodeResource(getResources(), R.drawable.ccapusman);
        bm[3]= BitmapFactory.decodeResource(getResources(), R.drawable.dcapusman);
        bm[4]= BitmapFactory.decodeResource(getResources(), R.drawable.ecapusman);
        //bm[40]= BitmapFactory.decodeResource(getResources(), R.drawable.ep1na);
        //bm[41]= BitmapFactory.decodeResource(getResources(), R.drawable.ep1nb);
        bm[5]= BitmapFactory.decodeResource(getResources(), R.drawable.fcapusman);
        bm[6]= BitmapFactory.decodeResource(getResources(), R.drawable.gcapusman);
        bm[7]= BitmapFactory.decodeResource(getResources(), R.drawable.hcapusman);
        //bm[45]= BitmapFactory.decodeResource(getResources(), R.drawable.hp1na);
        bm[8]= BitmapFactory.decodeResource(getResources(), R.drawable.icapusman);
        bm[9]= BitmapFactory.decodeResource(getResources(), R.drawable.jcapusman);
        bm[10]= BitmapFactory.decodeResource(getResources(), R.drawable.kcapusman);
        bm[11]= BitmapFactory.decodeResource(getResources(), R.drawable.lcapusman);
        bm[12]= BitmapFactory.decodeResource(getResources(), R.drawable.mcapusman);
        bm[13]= BitmapFactory.decodeResource(getResources(), R.drawable.ncapusman);
        bm[14]= BitmapFactory.decodeResource(getResources(), R.drawable.ocapusman);
        //bm[53]= BitmapFactory.decodeResource(getResources(), R.drawable.op1na);
        //bm[54]= BitmapFactory.decodeResource(getResources(), R.drawable.op1nb);
        //bm[55]= BitmapFactory.decodeResource(getResources(), R.drawable.op1nc);
        bm[15]= BitmapFactory.decodeResource(getResources(), R.drawable.pcapusman);
        bm[16]= BitmapFactory.decodeResource(getResources(), R.drawable.qcapusman);
        bm[17]= BitmapFactory.decodeResource(getResources(), R.drawable.rcapusman);
        //bm[59]= BitmapFactory.decodeResource(getResources(), R.drawable.rp1na);
        bm[18]= BitmapFactory.decodeResource(getResources(), R.drawable.scapusman);
        bm[19]= BitmapFactory.decodeResource(getResources(), R.drawable.tcapusman);
        //bm[62]= BitmapFactory.decodeResource(getResources(), R.drawable.tp1na);
        bm[20]= BitmapFactory.decodeResource(getResources(), R.drawable.ucapusman);
        //bm[64]= BitmapFactory.decodeResource(getResources(), R.drawable.up1na);
        bm[21]= BitmapFactory.decodeResource(getResources(), R.drawable.vcapusman);
        bm[22]= BitmapFactory.decodeResource(getResources(), R.drawable.wcapusman);
        bm[23]= BitmapFactory.decodeResource(getResources(), R.drawable.xcapusman);
        bm[24]= BitmapFactory.decodeResource(getResources(), R.drawable.ycapusman);
        bm[25]= BitmapFactory.decodeResource(getResources(), R.drawable.zcapusman);
        
        bm[26]= BitmapFactory.decodeResource(getResources(), R.drawable.ausman);
        bm[27]= BitmapFactory.decodeResource(getResources(), R.drawable.busman);
        bm[28]= BitmapFactory.decodeResource(getResources(), R.drawable.cusman);
        bm[29]= BitmapFactory.decodeResource(getResources(), R.drawable.dusman);
        bm[30]= BitmapFactory.decodeResource(getResources(), R.drawable.eusman);
        //bm[5]= BitmapFactory.decodeResource(getResources(), R.drawable.ecapn1a);
        //bm[6]= BitmapFactory.decodeResource(getResources(), R.drawable.ecapn1b);
        bm[31]= BitmapFactory.decodeResource(getResources(), R.drawable.fusman);
        bm[32]= BitmapFactory.decodeResource(getResources(), R.drawable.gusman);
        bm[33]= BitmapFactory.decodeResource(getResources(), R.drawable.husman);
        //bm[10]= BitmapFactory.decodeResource(getResources(), R.drawable.hcapn1a);
        bm[34]= BitmapFactory.decodeResource(getResources(), R.drawable.iusman);
        bm[35]= BitmapFactory.decodeResource(getResources(), R.drawable.jusman);
        bm[36]= BitmapFactory.decodeResource(getResources(), R.drawable.kusman);
        bm[37]= BitmapFactory.decodeResource(getResources(), R.drawable.lusman);
        bm[38]= BitmapFactory.decodeResource(getResources(), R.drawable.musman);
        bm[39]= BitmapFactory.decodeResource(getResources(), R.drawable.nusman);
        bm[40]= BitmapFactory.decodeResource(getResources(), R.drawable.ousman);
        //bm18]= BitmapFactory.decodeResource(getResources(), R.drawable.ocapn1a);
        //bm[19]= BitmapFactory.decodeResource(getResources(), R.drawable.ocapn1b);
        //bm[20]= BitmapFactory.decodeResource(getResources(), R.drawable.ocapn1c);
        bm[41]= BitmapFactory.decodeResource(getResources(), R.drawable.pusman);
        bm[42]= BitmapFactory.decodeResource(getResources(), R.drawable.qusman);
        bm[43]= BitmapFactory.decodeResource(getResources(), R.drawable.rusman);
        //bm[24]= BitmapFactory.decodeResource(getResources(), R.drawable.rcapn1a);
        bm[44]= BitmapFactory.decodeResource(getResources(), R.drawable.susman);
        bm[45]= BitmapFactory.decodeResource(getResources(), R.drawable.tusman);
        //bm[27]= BitmapFactory.decodeResource(getResources(), R.drawable.tcapn1a);
        bm[46]= BitmapFactory.decodeResource(getResources(), R.drawable.uusman);
        //bm[29]= BitmapFactory.decodeResource(getResources(), R.drawable.ucapn1a);
        bm[47]= BitmapFactory.decodeResource(getResources(), R.drawable.vusman);
        bm[48]= BitmapFactory.decodeResource(getResources(), R.drawable.wusman);
        bm[49]= BitmapFactory.decodeResource(getResources(), R.drawable.xusman);
        bm[50]= BitmapFactory.decodeResource(getResources(), R.drawable.yusman);
        bm[51]= BitmapFactory.decodeResource(getResources(), R.drawable.zusman);
       
        bm[52]= BitmapFactory.decodeResource(getResources(), R.drawable.zerousman);
        bm[53]= BitmapFactory.decodeResource(getResources(), R.drawable.oneusman);
        bm[54]= BitmapFactory.decodeResource(getResources(), R.drawable.twousman);
        bm[55]= BitmapFactory.decodeResource(getResources(), R.drawable.threeusman);
        bm[56]= BitmapFactory.decodeResource(getResources(), R.drawable.fourusman);
        bm[57]= BitmapFactory.decodeResource(getResources(), R.drawable.fiveusman);
        bm[58]= BitmapFactory.decodeResource(getResources(), R.drawable.sixusman);
        bm[59]= BitmapFactory.decodeResource(getResources(), R.drawable.sevenusman);
        bm[60]= BitmapFactory.decodeResource(getResources(), R.drawable.eightusman);
        bm[61]= BitmapFactory.decodeResource(getResources(), R.drawable.nineusman);*/
        
        /*bm[0]= BitmapFactory.decodeResource(getResources(), R.drawable.acapn1);
        bm[1]= BitmapFactory.decodeResource(getResources(), R.drawable.bcapn1);
        bm[2]= BitmapFactory.decodeResource(getResources(), R.drawable.ccapn1);
        bm[3]= BitmapFactory.decodeResource(getResources(), R.drawable.dcapn1);
        bm[4]= BitmapFactory.decodeResource(getResources(), R.drawable.ecapn1);
        //bm[5]= BitmapFactory.decodeResource(getResources(), R.drawable.ecapn1a);
        //bm[6]= BitmapFactory.decodeResource(getResources(), R.drawable.ecapn1b);
        bm[5]= BitmapFactory.decodeResource(getResources(), R.drawable.fcapn1);
        bm[6]= BitmapFactory.decodeResource(getResources(), R.drawable.gcapn1);
        bm[7]= BitmapFactory.decodeResource(getResources(), R.drawable.hcapn1);
        //bm[10]= BitmapFactory.decodeResource(getResources(), R.drawable.hcapn1a);
        bm[8]= BitmapFactory.decodeResource(getResources(), R.drawable.icapn1);
        bm[9]= BitmapFactory.decodeResource(getResources(), R.drawable.jcapn1);
        bm[10]= BitmapFactory.decodeResource(getResources(), R.drawable.kcapn1);
        bm[11]= BitmapFactory.decodeResource(getResources(), R.drawable.lcapn1);
        bm[12]= BitmapFactory.decodeResource(getResources(), R.drawable.mcapn1);
        bm[13]= BitmapFactory.decodeResource(getResources(), R.drawable.ncapn1);
        bm[14]= BitmapFactory.decodeResource(getResources(), R.drawable.ocapn1);
        //bm18]= BitmapFactory.decodeResource(getResources(), R.drawable.ocapn1a);
        //bm[19]= BitmapFactory.decodeResource(getResources(), R.drawable.ocapn1b);
        //bm[20]= BitmapFactory.decodeResource(getResources(), R.drawable.ocapn1c);
        bm[15]= BitmapFactory.decodeResource(getResources(), R.drawable.pcapn1);
        bm[16]= BitmapFactory.decodeResource(getResources(), R.drawable.qcapn1);
        bm[17]= BitmapFactory.decodeResource(getResources(), R.drawable.rcapn1);
        //bm[24]= BitmapFactory.decodeResource(getResources(), R.drawable.rcapn1a);
        bm[18]= BitmapFactory.decodeResource(getResources(), R.drawable.scapn1);
        bm[19]= BitmapFactory.decodeResource(getResources(), R.drawable.tcapn1);
        //bm[27]= BitmapFactory.decodeResource(getResources(), R.drawable.tcapn1a);
        bm[20]= BitmapFactory.decodeResource(getResources(), R.drawable.ucapn1);
        //bm[29]= BitmapFactory.decodeResource(getResources(), R.drawable.ucapn1a);
        bm[21]= BitmapFactory.decodeResource(getResources(), R.drawable.vcapn1);
        bm[22]= BitmapFactory.decodeResource(getResources(), R.drawable.wcapn1);
        bm[23]= BitmapFactory.decodeResource(getResources(), R.drawable.xcapn1);
        bm[24]= BitmapFactory.decodeResource(getResources(), R.drawable.ycapn1);
        bm[25]= BitmapFactory.decodeResource(getResources(), R.drawable.zcapn1);
        
        bm[26]= BitmapFactory.decodeResource(getResources(), R.drawable.ap1n);
        bm[27]= BitmapFactory.decodeResource(getResources(), R.drawable.bp1n);
        bm[28]= BitmapFactory.decodeResource(getResources(), R.drawable.cp1n);
        bm[29]= BitmapFactory.decodeResource(getResources(), R.drawable.dp1n);
        bm[30]= BitmapFactory.decodeResource(getResources(), R.drawable.ep1n);
        //bm[40]= BitmapFactory.decodeResource(getResources(), R.drawable.ep1na);
        //bm[41]= BitmapFactory.decodeResource(getResources(), R.drawable.ep1nb);
        bm[31]= BitmapFactory.decodeResource(getResources(), R.drawable.fp1n);
        bm[32]= BitmapFactory.decodeResource(getResources(), R.drawable.gp1n);
        bm[33]= BitmapFactory.decodeResource(getResources(), R.drawable.hp1n);
        //bm[45]= BitmapFactory.decodeResource(getResources(), R.drawable.hp1na);
        bm[34]= BitmapFactory.decodeResource(getResources(), R.drawable.ip1n);
        bm[35]= BitmapFactory.decodeResource(getResources(), R.drawable.jp1n);
        bm[36]= BitmapFactory.decodeResource(getResources(), R.drawable.kp1n);
        bm[37]= BitmapFactory.decodeResource(getResources(), R.drawable.lp1n);
        bm[38]= BitmapFactory.decodeResource(getResources(), R.drawable.mp1n);
        bm[39]= BitmapFactory.decodeResource(getResources(), R.drawable.np1n);
        bm[40]= BitmapFactory.decodeResource(getResources(), R.drawable.op1n);
        //bm[53]= BitmapFactory.decodeResource(getResources(), R.drawable.op1na);
        //bm[54]= BitmapFactory.decodeResource(getResources(), R.drawable.op1nb);
        //bm[55]= BitmapFactory.decodeResource(getResources(), R.drawable.op1nc);
        bm[41]= BitmapFactory.decodeResource(getResources(), R.drawable.pp1n);
        bm[42]= BitmapFactory.decodeResource(getResources(), R.drawable.qp1n);
        bm[43]= BitmapFactory.decodeResource(getResources(), R.drawable.rp1n);
        //bm[59]= BitmapFactory.decodeResource(getResources(), R.drawable.rp1na);
        bm[44]= BitmapFactory.decodeResource(getResources(), R.drawable.sp1n);
        bm[45]= BitmapFactory.decodeResource(getResources(), R.drawable.tp1n);
        //bm[62]= BitmapFactory.decodeResource(getResources(), R.drawable.tp1na);
        bm[46]= BitmapFactory.decodeResource(getResources(), R.drawable.up1n);
        //bm[64]= BitmapFactory.decodeResource(getResources(), R.drawable.up1na);
        bm[47]= BitmapFactory.decodeResource(getResources(), R.drawable.vp1n);
        bm[48]= BitmapFactory.decodeResource(getResources(), R.drawable.wp1n);
        bm[49]= BitmapFactory.decodeResource(getResources(), R.drawable.xp1n);
        bm[50]= BitmapFactory.decodeResource(getResources(), R.drawable.yp1n);
        bm[51]= BitmapFactory.decodeResource(getResources(), R.drawable.zp1n);
        
        bm[52]= BitmapFactory.decodeResource(getResources(), R.drawable.zeropn1);
        bm[53]= BitmapFactory.decodeResource(getResources(), R.drawable.onepn1);
        bm[54]= BitmapFactory.decodeResource(getResources(), R.drawable.twopn1);
        bm[55]= BitmapFactory.decodeResource(getResources(), R.drawable.threepn1);
        bm[56]= BitmapFactory.decodeResource(getResources(), R.drawable.fourpn1);
        bm[57]= BitmapFactory.decodeResource(getResources(), R.drawable.fivepn1);
        bm[58]= BitmapFactory.decodeResource(getResources(), R.drawable.sixpn1);
        bm[59]= BitmapFactory.decodeResource(getResources(), R.drawable.sevenpn1);
        bm[60]= BitmapFactory.decodeResource(getResources(), R.drawable.eightpn1);
        bm[61]= BitmapFactory.decodeResource(getResources(), R.drawable.ninepn1);
       
        
        bm[62]= BitmapFactory.decodeResource(getResources(), R.drawable.ap1capz);
        bm[63]= BitmapFactory.decodeResource(getResources(), R.drawable.bp1capz);
        bm[64]= BitmapFactory.decodeResource(getResources(), R.drawable.cp1capz);
        bm[65]= BitmapFactory.decodeResource(getResources(), R.drawable.dp1capz);
        bm[66]= BitmapFactory.decodeResource(getResources(), R.drawable.ep1capz);
        //bm[85]= BitmapFactory.decodeResource(getResources(), R.drawable.e2p1z);
        //bm[86]= BitmapFactory.decodeResource(getResources(), R.drawable.e3p1z);
        bm[67]= BitmapFactory.decodeResource(getResources(), R.drawable.fp1capz);
        bm[68]= BitmapFactory.decodeResource(getResources(), R.drawable.gp1capz);
        bm[69]= BitmapFactory.decodeResource(getResources(), R.drawable.hp1capz);
        //bm[90]= BitmapFactory.decodeResource(getResources(), R.drawable.h2p1z);
        bm[70]= BitmapFactory.decodeResource(getResources(), R.drawable.ip1capz);
        bm[71]= BitmapFactory.decodeResource(getResources(), R.drawable.jp1capz);
        bm[72]= BitmapFactory.decodeResource(getResources(), R.drawable.kp1capz);
        bm[73]= BitmapFactory.decodeResource(getResources(), R.drawable.lp1capz);
        bm[74]= BitmapFactory.decodeResource(getResources(), R.drawable.mp1capz);
        bm[75]= BitmapFactory.decodeResource(getResources(), R.drawable.np1capz);
        bm[76]= BitmapFactory.decodeResource(getResources(), R.drawable.op1capz);
        //bm[98]= BitmapFactory.decodeResource(getResources(), R.drawable.o2p1z);
        //bm[99]= BitmapFactory.decodeResource(getResources(), R.drawable.o3p1z);
        //bm[100]= BitmapFactory.decodeResource(getResources(), R.drawable.o4p1z);
        bm[77]= BitmapFactory.decodeResource(getResources(), R.drawable.pp1capz);
        bm[78]= BitmapFactory.decodeResource(getResources(), R.drawable.qp1capz);
        bm[79]= BitmapFactory.decodeResource(getResources(), R.drawable.rp1capz);
        //bm[104]= BitmapFactory.decodeResource(getResources(), R.drawable.r2p1z);
        bm[80]= BitmapFactory.decodeResource(getResources(), R.drawable.sp1capz);
        bm[81]= BitmapFactory.decodeResource(getResources(), R.drawable.tp1capz);
        //bm[107]= BitmapFactory.decodeResource(getResources(), R.drawable.t2p1z);
        bm[82]= BitmapFactory.decodeResource(getResources(), R.drawable.up1capz);
        //bm[109]= BitmapFactory.decodeResource(getResources(), R.drawable.u2p1z);
        bm[83]= BitmapFactory.decodeResource(getResources(), R.drawable.vp1capz);
        bm[84]= BitmapFactory.decodeResource(getResources(), R.drawable.wp1capz);
        bm[85]= BitmapFactory.decodeResource(getResources(), R.drawable.xp1capz);
        bm[86]= BitmapFactory.decodeResource(getResources(), R.drawable.yp1capz);
        bm[87]= BitmapFactory.decodeResource(getResources(), R.drawable.zp1capz);
        
        bm[88]= BitmapFactory.decodeResource(getResources(), R.drawable.ap1z);
        bm[89]= BitmapFactory.decodeResource(getResources(), R.drawable.bp1z);
        bm[90]= BitmapFactory.decodeResource(getResources(), R.drawable.cp1z);
        bm[91]= BitmapFactory.decodeResource(getResources(), R.drawable.dp1z);
        bm[92]= BitmapFactory.decodeResource(getResources(), R.drawable.ep1z);
        //bm[85]= BitmapFactory.decodeResource(getResources(), R.drawable.e2p1z);
        //bm[86]= BitmapFactory.decodeResource(getResources(), R.drawable.e3p1z);
        bm[93]= BitmapFactory.decodeResource(getResources(), R.drawable.fp1z);
        bm[94]= BitmapFactory.decodeResource(getResources(), R.drawable.gp1z);
        bm[95]= BitmapFactory.decodeResource(getResources(), R.drawable.hp1z);
        //bm[90]= BitmapFactory.decodeResource(getResources(), R.drawable.h2p1z);
        bm[96]= BitmapFactory.decodeResource(getResources(), R.drawable.ip1z);
        bm[97]= BitmapFactory.decodeResource(getResources(), R.drawable.jp1z);
        bm[98]= BitmapFactory.decodeResource(getResources(), R.drawable.kp1z);
        bm[99]= BitmapFactory.decodeResource(getResources(), R.drawable.lp1z);
        bm[100]= BitmapFactory.decodeResource(getResources(), R.drawable.mp1z);
        bm[101]= BitmapFactory.decodeResource(getResources(), R.drawable.np1z);
        bm[102]= BitmapFactory.decodeResource(getResources(), R.drawable.op1z);
        //bm[98]= BitmapFactory.decodeResource(getResources(), R.drawable.o2p1z);
        //bm[99]= BitmapFactory.decodeResource(getResources(), R.drawable.o3p1z);
        //bm[100]= BitmapFactory.decodeResource(getResources(), R.drawable.o4p1z);
        bm[103]= BitmapFactory.decodeResource(getResources(), R.drawable.pp1z);
        bm[104]= BitmapFactory.decodeResource(getResources(), R.drawable.qp1z);
        bm[105]= BitmapFactory.decodeResource(getResources(), R.drawable.rp1z);
        //bm[104]= BitmapFactory.decodeResource(getResources(), R.drawable.r2p1z);
        bm[106]= BitmapFactory.decodeResource(getResources(), R.drawable.sp1z);
        bm[107]= BitmapFactory.decodeResource(getResources(), R.drawable.tp1z);
        //bm[107]= BitmapFactory.decodeResource(getResources(), R.drawable.t2p1z);
        bm[108]= BitmapFactory.decodeResource(getResources(), R.drawable.up1z);
        //bm[109]= BitmapFactory.decodeResource(getResources(), R.drawable.u2p1z);
        bm[109]= BitmapFactory.decodeResource(getResources(), R.drawable.vp1z);
        bm[110]= BitmapFactory.decodeResource(getResources(), R.drawable.wp1z);
        bm[111]= BitmapFactory.decodeResource(getResources(), R.drawable.xp1z);
        bm[112]= BitmapFactory.decodeResource(getResources(), R.drawable.yp1z);
        bm[113]= BitmapFactory.decodeResource(getResources(), R.drawable.zp1z);
        
        bm[114]= BitmapFactory.decodeResource(getResources(), R.drawable.zerop1z);
        bm[115]= BitmapFactory.decodeResource(getResources(), R.drawable.onep1z);
        bm[116]= BitmapFactory.decodeResource(getResources(), R.drawable.twop1z);
        bm[117]= BitmapFactory.decodeResource(getResources(), R.drawable.threep1z);
        bm[118]= BitmapFactory.decodeResource(getResources(), R.drawable.fourp1z);
        bm[119]= BitmapFactory.decodeResource(getResources(), R.drawable.fivep1z);
        bm[120]= BitmapFactory.decodeResource(getResources(), R.drawable.sixp1z);
        bm[121]= BitmapFactory.decodeResource(getResources(), R.drawable.sevenp1z);
        bm[122]= BitmapFactory.decodeResource(getResources(), R.drawable.eightp1z);
        bm[123]= BitmapFactory.decodeResource(getResources(), R.drawable.ninep1z);*/
        
        
        /*bm[35]= BitmapFactory.decodeResource(getResources(), R.drawable.ap2z);
        bm[36]= BitmapFactory.decodeResource(getResources(), R.drawable.bp2z);
        bm[37]= BitmapFactory.decodeResource(getResources(), R.drawable.cp2z);
        bm[38]= BitmapFactory.decodeResource(getResources(), R.drawable.dp2z);
        bm[39]= BitmapFactory.decodeResource(getResources(), R.drawable.ep2z);
        bm[40]= BitmapFactory.decodeResource(getResources(), R.drawable.e2p2z);
        bm[41]= BitmapFactory.decodeResource(getResources(), R.drawable.e3p2z);
        bm[42]= BitmapFactory.decodeResource(getResources(), R.drawable.fp2z);
        bm[43]= BitmapFactory.decodeResource(getResources(), R.drawable.gp2z);
        bm[44]= BitmapFactory.decodeResource(getResources(), R.drawable.hp2z);
        bm[45]= BitmapFactory.decodeResource(getResources(), R.drawable.h2p2z);
        bm[46]= BitmapFactory.decodeResource(getResources(), R.drawable.ip2z);
        bm[47]= BitmapFactory.decodeResource(getResources(), R.drawable.jp2z);
        bm[48]= BitmapFactory.decodeResource(getResources(), R.drawable.kp2z);
        bm[49]= BitmapFactory.decodeResource(getResources(), R.drawable.lp2z);
        bm[50]= BitmapFactory.decodeResource(getResources(), R.drawable.mp2z);
        bm[51]= BitmapFactory.decodeResource(getResources(), R.drawable.np2z);
        bm[52]= BitmapFactory.decodeResource(getResources(), R.drawable.op2z);
        bm[53]= BitmapFactory.decodeResource(getResources(), R.drawable.o2p1z);
        bm[54]= BitmapFactory.decodeResource(getResources(), R.drawable.o3p1z);
        bm[55]= BitmapFactory.decodeResource(getResources(), R.drawable.o4p1z);
        bm[56]= BitmapFactory.decodeResource(getResources(), R.drawable.pp2z);
        bm[57]= BitmapFactory.decodeResource(getResources(), R.drawable.qp2z);
        bm[58]= BitmapFactory.decodeResource(getResources(), R.drawable.rp2z);
        bm[59]= BitmapFactory.decodeResource(getResources(), R.drawable.r2p1z);
        bm[60]= BitmapFactory.decodeResource(getResources(), R.drawable.sp2z);
        bm[61]= BitmapFactory.decodeResource(getResources(), R.drawable.tp2z);
        bm[62]= BitmapFactory.decodeResource(getResources(), R.drawable.t2p1z);
        bm[63]= BitmapFactory.decodeResource(getResources(), R.drawable.up2z);
        bm[64]= BitmapFactory.decodeResource(getResources(), R.drawable.u2p1z);
        bm[65]= BitmapFactory.decodeResource(getResources(), R.drawable.vp2z);
        bm[66]= BitmapFactory.decodeResource(getResources(), R.drawable.wp2z);
        bm[67]= BitmapFactory.decodeResource(getResources(), R.drawable.xp2z);
        bm[68]= BitmapFactory.decodeResource(getResources(), R.drawable.yp2z);
        bm[69]= BitmapFactory.decodeResource(getResources(), R.drawable.zp2z);
        
        bm[70]= BitmapFactory.decodeResource(getResources(), R.drawable.ap4z);
        bm[71]= BitmapFactory.decodeResource(getResources(), R.drawable.bp4z);
        bm[72]= BitmapFactory.decodeResource(getResources(), R.drawable.cp4z);
        bm[73]= BitmapFactory.decodeResource(getResources(), R.drawable.dp4z);
        bm[74]= BitmapFactory.decodeResource(getResources(), R.drawable.ep4z);
        bm[75]= BitmapFactory.decodeResource(getResources(), R.drawable.e2p4z);
        bm[76]= BitmapFactory.decodeResource(getResources(), R.drawable.e3p4z);
        bm[77]= BitmapFactory.decodeResource(getResources(), R.drawable.fp4z);
        bm[78]= BitmapFactory.decodeResource(getResources(), R.drawable.gp4z);
        bm[79]= BitmapFactory.decodeResource(getResources(), R.drawable.hp4z);
        bm[80]= BitmapFactory.decodeResource(getResources(), R.drawable.h2p4z);
        bm[81]= BitmapFactory.decodeResource(getResources(), R.drawable.ip4z);
        bm[82]= BitmapFactory.decodeResource(getResources(), R.drawable.jp4z);
        bm[83]= BitmapFactory.decodeResource(getResources(), R.drawable.kp4z);
        bm[84]= BitmapFactory.decodeResource(getResources(), R.drawable.lp4z);
        bm[85]= BitmapFactory.decodeResource(getResources(), R.drawable.mp4z);
        bm[86]= BitmapFactory.decodeResource(getResources(), R.drawable.np4z);
        bm[87]= BitmapFactory.decodeResource(getResources(), R.drawable.op4z);
        bm[88]= BitmapFactory.decodeResource(getResources(), R.drawable.o2p4z);
        bm[89]= BitmapFactory.decodeResource(getResources(), R.drawable.o3p4z);
        bm[90]= BitmapFactory.decodeResource(getResources(), R.drawable.o4p4z);
        bm[91]= BitmapFactory.decodeResource(getResources(), R.drawable.pp4z);
        bm[92]= BitmapFactory.decodeResource(getResources(), R.drawable.qp4z);
        bm[93]= BitmapFactory.decodeResource(getResources(), R.drawable.rp4z);
        bm[94]= BitmapFactory.decodeResource(getResources(), R.drawable.r2p4z);
        bm[95]= BitmapFactory.decodeResource(getResources(), R.drawable.sp4z);
        bm[96]= BitmapFactory.decodeResource(getResources(), R.drawable.tp4z);
        bm[97]= BitmapFactory.decodeResource(getResources(), R.drawable.t2p4z);
        bm[98]= BitmapFactory.decodeResource(getResources(), R.drawable.up4z);
        bm[99]= BitmapFactory.decodeResource(getResources(), R.drawable.u2p4z);
        bm[100]= BitmapFactory.decodeResource(getResources(), R.drawable.vp4z);
        bm[101]= BitmapFactory.decodeResource(getResources(), R.drawable.wp4z);
        bm[102]= BitmapFactory.decodeResource(getResources(), R.drawable.xp4z);
        bm[103]= BitmapFactory.decodeResource(getResources(), R.drawable.yp4z);
        bm[104]= BitmapFactory.decodeResource(getResources(), R.drawable.zp4z);
        
        bm[105]= BitmapFactory.decodeResource(getResources(), R.drawable.ap3z);
        bm[106]= BitmapFactory.decodeResource(getResources(), R.drawable.bp3z);
        bm[107]= BitmapFactory.decodeResource(getResources(), R.drawable.cp3z);
        bm[108]= BitmapFactory.decodeResource(getResources(), R.drawable.dp3z);
        bm[109]= BitmapFactory.decodeResource(getResources(), R.drawable.ep3z);
        bm[110]= BitmapFactory.decodeResource(getResources(), R.drawable.e2p3z);
        bm[111]= BitmapFactory.decodeResource(getResources(), R.drawable.e3p3z);
        bm[112]= BitmapFactory.decodeResource(getResources(), R.drawable.fp3z);
        bm[113]= BitmapFactory.decodeResource(getResources(), R.drawable.gp3z);
        bm[114]= BitmapFactory.decodeResource(getResources(), R.drawable.hp3z);
        bm[115]= BitmapFactory.decodeResource(getResources(), R.drawable.h2p3z);
        bm[116]= BitmapFactory.decodeResource(getResources(), R.drawable.ip3z);
        bm[117]= BitmapFactory.decodeResource(getResources(), R.drawable.jp3z);
        bm[118]= BitmapFactory.decodeResource(getResources(), R.drawable.kp3z);
        bm[119]= BitmapFactory.decodeResource(getResources(), R.drawable.lp3z);
        bm[120]= BitmapFactory.decodeResource(getResources(), R.drawable.mp3z);
        bm[121]= BitmapFactory.decodeResource(getResources(), R.drawable.np3z);
        bm[122]= BitmapFactory.decodeResource(getResources(), R.drawable.op3z);
        bm[123]= BitmapFactory.decodeResource(getResources(), R.drawable.o2p3z);
        bm[124]= BitmapFactory.decodeResource(getResources(), R.drawable.o3p3z);
        bm[125]= BitmapFactory.decodeResource(getResources(), R.drawable.o4p3z);
        bm[126]= BitmapFactory.decodeResource(getResources(), R.drawable.pp3z);
        bm[127]= BitmapFactory.decodeResource(getResources(), R.drawable.qp3z);
        bm[128]= BitmapFactory.decodeResource(getResources(), R.drawable.rp3z);
        bm[129]= BitmapFactory.decodeResource(getResources(), R.drawable.r2p3z);
        bm[130]= BitmapFactory.decodeResource(getResources(), R.drawable.sp3z);
        bm[131]= BitmapFactory.decodeResource(getResources(), R.drawable.tp3z);
        bm[132]= BitmapFactory.decodeResource(getResources(), R.drawable.t2p3z);
        bm[133]= BitmapFactory.decodeResource(getResources(), R.drawable.up3z);
        bm[134]= BitmapFactory.decodeResource(getResources(), R.drawable.u2p3z);
        bm[135]= BitmapFactory.decodeResource(getResources(), R.drawable.vp3z);
        bm[136]= BitmapFactory.decodeResource(getResources(), R.drawable.wp3z);
        bm[137]= BitmapFactory.decodeResource(getResources(), R.drawable.xp3z);
        bm[138]= BitmapFactory.decodeResource(getResources(), R.drawable.yp3z);
        bm[139]= BitmapFactory.decodeResource(getResources(), R.drawable.zp3z);
        
        bm[140]= BitmapFactory.decodeResource(getResources(), R.drawable.apz1);
        bm[141]= BitmapFactory.decodeResource(getResources(), R.drawable.bpz1);
        bm[142]= BitmapFactory.decodeResource(getResources(), R.drawable.cpz1);
        bm[143]= BitmapFactory.decodeResource(getResources(), R.drawable.dpz1);
        bm[144]= BitmapFactory.decodeResource(getResources(), R.drawable.epz1);
        bm[145]= BitmapFactory.decodeResource(getResources(), R.drawable.e2pz1);
        bm[146]= BitmapFactory.decodeResource(getResources(), R.drawable.e3pz1);
        bm[147]= BitmapFactory.decodeResource(getResources(), R.drawable.fpz1);
        bm[148]= BitmapFactory.decodeResource(getResources(), R.drawable.gpz1);
        bm[149]= BitmapFactory.decodeResource(getResources(), R.drawable.hpz1);
        bm[150]= BitmapFactory.decodeResource(getResources(), R.drawable.h2pz1);
        bm[151]= BitmapFactory.decodeResource(getResources(), R.drawable.ipz1);
        bm[152]= BitmapFactory.decodeResource(getResources(), R.drawable.jpz1);
        bm[153]= BitmapFactory.decodeResource(getResources(), R.drawable.kpz1);
        bm[154]= BitmapFactory.decodeResource(getResources(), R.drawable.lpz1);
        bm[155]= BitmapFactory.decodeResource(getResources(), R.drawable.mpz1);
        bm[156]= BitmapFactory.decodeResource(getResources(), R.drawable.npz1);
        bm[157]= BitmapFactory.decodeResource(getResources(), R.drawable.opz1);
        bm[158]= BitmapFactory.decodeResource(getResources(), R.drawable.o2pz1);
        bm[159]= BitmapFactory.decodeResource(getResources(), R.drawable.o3pz1);
        bm[160]= BitmapFactory.decodeResource(getResources(), R.drawable.o4pz1);
        bm[161]= BitmapFactory.decodeResource(getResources(), R.drawable.ppz1);
        bm[162]= BitmapFactory.decodeResource(getResources(), R.drawable.qpz1);
        bm[163]= BitmapFactory.decodeResource(getResources(), R.drawable.rpz1);
        bm[164]= BitmapFactory.decodeResource(getResources(), R.drawable.r2pz1);
        bm[165]= BitmapFactory.decodeResource(getResources(), R.drawable.spz1);
        bm[166]= BitmapFactory.decodeResource(getResources(), R.drawable.tpz1);
        bm[167]= BitmapFactory.decodeResource(getResources(), R.drawable.t2pz1);
        bm[168]= BitmapFactory.decodeResource(getResources(), R.drawable.upz1);
        bm[169]= BitmapFactory.decodeResource(getResources(), R.drawable.u2pz1);
        bm[170]= BitmapFactory.decodeResource(getResources(), R.drawable.vpz1);
        bm[171]= BitmapFactory.decodeResource(getResources(), R.drawable.wpz1);
        bm[172]= BitmapFactory.decodeResource(getResources(), R.drawable.xpz1);
        bm[173]= BitmapFactory.decodeResource(getResources(), R.drawable.ypz1);
        bm[174]= BitmapFactory.decodeResource(getResources(), R.drawable.zpz1);*/
        
       if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) 
    	{
    		Log.d("MOUNTED", "Sdcard was not mounted !!" ); 
        }
    	else
        {
    		File nmea_file; 
            File root = Environment.getExternalStorageDirectory();
            FileWriter nmea_writer = null;
            try 
            {
            	nmea_file = new File(root,"Vector.txt");
                if(!nmea_file.exists()) 
                {
                	Log.w("File not Found", "File Doesn't Exists!");
                    nmea_file.createNewFile();
                }
                nmea_writer = new FileWriter(nmea_file);
		        for(int i=0;i<bm.length;i++)
		        {
			        bmp = bm[i];
			        img.setImageBitmap(bmp);
			        bmp =getAdaptiveLocalBinarization(17,14,bmp);
			     	int roi[] = regionOfIntrest(bmp, bmp.getWidth(), bmp.getHeight());//getting of interest from image into an array
			    	bmp 	  = getCropedRegion(bmp, roi[0], roi[1], roi[2], roi[3]);//returning selected region(cropped) 
			        int picw=bmp.getWidth();
			        int pich=bmp.getHeight();
			    	int[] pix = new int[picw * pich];
				 	TextView text = (TextView) findViewById(R.id.editText1);
				 	int R=0, G=0, B=0,index=0;
			    	int[] structure = new int[picw * pich];
			    	img.setImageBitmap(bmp);
			    	Bitmap bmp1 = rescaleer(bmp,rewidth,reheight);
			    	
			    	img2.setImageBitmap(bmp1);
			        
			
			    	bmp1.getPixels(pix, 0, bmp1.getWidth(), 0, 0, bmp1.getWidth(), bmp1.getHeight());

			    	for (int y = 0; y < bmp1.getHeight(); y++)
			        {
			        	for (int x = 0; x <  bmp1.getWidth(); x++)
			            {
			        		 index = y *  bmp1.getWidth() + x;   	
			        	     R = (pix[index] >> 16) & 0xff;
			        		 G = (pix[index] >> 8) & 0xff;
			        		 B =  pix[index] & 0xff;
			        		 
			        		 if(R==0 && G==0 && B==0)
			        		 {
			        			 structure[index] = 1;
			        		 }
			        		 else
			        		 {
			        			structure[index] = 0;
			        		 }
			            }
			        }
			    	
			        text.append(" New image "+i+"\n");
						for(int x=0;x<rewidth*reheight;x++)
						{
							//text.append(" "+structure[x]);
							nmea_writer.append(structure[x]+",");
						}
						nmea_writer.append("\t");
						//for(int out=0;out<26;out++)
						/*{
							
							{
								nmea_writer.append("0,0,0,0,0,0," +
												   "0,0,0,0,0,0," +
												   "0,0,0,0,0,0," +
												   "0,0,0,0,0,0," +
												   "0,0," +"\t"+
												   "0,0,0,0,0,0," +
												   "0,0,0,0,0,0," +
												   "0,0,0,0,0,0," +
												   "0,0,0,0,0,0," +
												   "0,0," +"\t"+
												   "0,0,0,0,0,0," +
												   "0,0,0,0");
							}
						}*/
						for(int out=0;out<62;out++)
						{
							if(out==(i%62))
							{
								nmea_writer.append(1+",");
							}
							else
							{
								nmea_writer.append(0+",");
							}
						}
						nmea_writer.append("\n");
	              }
	            }
			                	
            catch (IOException e) 
            {
                Log.w("Fiel write failed", "Unable to write", e);
            } 
            finally 
            {
            	try {
					nmea_writer.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                if (nmea_writer != null) 
                {
                    try 
                    {
                        nmea_writer.close();
                    } 
                    catch (IOException e) 
                    {
                        Log.w("Exception closing file", "Exception closing file", e);
                    }
                }
            }
        }
    }
    public Bitmap rescaleer(Bitmap m2,int m1w,int m1h)
    {
		int m2wi = m2.getWidth();
		int m2hi = m2.getHeight();
        int[] pix = new int[m2wi * m2hi];
        m2.getPixels(pix, 0, m2wi, 0, 0, m2wi, m2hi);
        int[] pixa = new int[m1w * m1h];
        pixa= resizePixels(pix, m2wi, m2hi, m1w,m1h);
        m2=Bitmap.createBitmap(pixa, m1w, m1h, Config.ARGB_8888);
    	return m2;
    }
    public int[] resizePixels(int[] pixels,int w1,int h1,int w2,int h2) {
    	int[] bm = new int[w2*h2] ;
        // EDIT: added +1 to account for an early rounding problem
        int x_ratio = (int)((w1<<16)/w2) +1;
        int y_ratio = (int)((h1<<16)/h2) +1;
        int x2, y2 ;
        for (int i=0;i<h2;i++) {
            for (int j=0;j<w2;j++) {
                x2 = ((j*x_ratio)>>16) ;
                y2 = ((i*y_ratio)>>16) ;
                bm[(i*w2)+j] = pixels[(y2*w1)+x2] ;
            }                
        }                
        return bm ;

    }
    
    public Bitmap getAdaptiveLocalBinarization(int blocksize, int constant, Bitmap bmp)
    {
        Mat imgToProcess2 = Utils.bitmapToMat(bmp);
	   Imgproc.GaussianBlur(imgToProcess2, imgToProcess2, new Size(3,3), 1, 1, Imgproc.BORDER_DEFAULT);
        Imgproc.cvtColor(imgToProcess2, imgToProcess2, Imgproc.COLOR_BGR2GRAY);
	    Imgproc.adaptiveThreshold(imgToProcess2, imgToProcess2,255,Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C,Imgproc.THRESH_BINARY,blocksize,constant);
        Imgproc.cvtColor(imgToProcess2, imgToProcess2, Imgproc.COLOR_GRAY2BGRA, 4);
	    Bitmap bmpOut2 = Bitmap.createBitmap(imgToProcess2.cols(), imgToProcess2.rows(), Bitmap.Config.ARGB_8888);
	    Utils.matToBitmap(imgToProcess2, bmpOut2);
		return bmpOut2;
    }
    
    public Bitmap getCropedRegion(Bitmap orignalImage , int x1 , int x2 , int y1 , int y2 )
	{
		int picw = orignalImage.getWidth();
		int pich = orignalImage.getHeight();
		Bitmap bmp = Bitmap.createBitmap(orignalImage, 0, 0, orignalImage.getWidth(), orignalImage.getHeight());

		int[] pix = new int[picw * pich];
	    bmp.getPixels(pix, 0, picw, 0, 0, picw, pich); 
		int index=0;
		int [] cop=new int[(y2-y1)*(x2-x1)];
	    int op=0;
	    for(int z=y1;z<y2;z++)
	    {
	    	for(int a=x1;a<x2;a++)
	       	{
		    		index=z*picw+a;
		    		cop[op] = pix[index];
		    		op++;
	        }
	    }
	  return ( Bitmap.createBitmap(cop, x2-x1, y2-y1, Config.ARGB_8888));
	}
    
    public int[] regionOfIntrest(Bitmap bmp ,int picw,int pich)
	{
	    int[] pix = new int[picw * pich];
	    int[] points = new int[4];
	    bmp.getPixels(pix, 0, picw, 0, 0, picw, pich); 
	    int R=0, G=0, B=0,index=0;
        int y1=0,y2=0,x1=0,x2=0;
        boolean check = true;
        
        for (int y = 0; y < pich; y++)
        {
        	for (int x = 0; x < picw; x++)
            {
        		 index = y * picw + x;
        		
        	     R = (pix[index] >> 16) & 0xff; //bitwise shifting
        		 G = (pix[index] >> 8) & 0xff;
        		 B =  pix[index] & 0xff;
        		 
        		 if(R == 0 && G == 0 && B == 0)
        		 {
           				 if(check==true)
           				 {
           					 x1=x;
           					 x2=x;
           					 check=false;
           				 }
           				 if(x1>x)
           				 {
           					 x1=x;
           				 }
           				 if(x2<x)
           				 {
           					x2=x; 
           				 }
        		 }
        		pix[index] = 0xff000000 | (R << 16) | (G << 8) | B;
        		
            }	
        }
        
        //***************************calculating y1 and y2 value************************
        check=true;
        for (int y = 0; y < picw; y++)
        {
        	for (int x = 0; x < pich; x++)
            {
        		 index = x * picw+y;
        	     R = (pix[index] >> 16) & 0xff; //bitwise shifting
        		 G = (pix[index] >> 8) & 0xff;
        		 B =  pix[index] & 0xff;
        		 
        		 if(R == 0 && G == 0 && B == 0)
        		 {
           				 if(check==true)
           				 {
           					 y1=x;
           					 y2=x;
           					 check=false;
           				 }
           				 if(y1>x)
           				 {
           					 y1=x;
           				 }
           				 if(y2<x)
           				 {
           					 y2=x;
           				 }
        		 }
        		pix[index] = 0xff000000 | (R << 16) | (G << 8) | B;
            }	
        }
      
        points[0]=x1;  points[1]=x2+1; points[2]=y1;points[3]=y2+1;
      
        return points;
	}
}