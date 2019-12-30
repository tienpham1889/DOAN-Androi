package com.example.quizzzzzz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class TroChoiActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private final ArrayList<LinhVuc> mListLinhVuc = new ArrayList<>();
    Button btnCallActivityCauHoi;
    private final ArrayList<LinhVuc> mListCauHoi = new ArrayList<>();
    private ImageButton btnCallActvityMain;
    private ImageButton btnGoiDien;
    CountDownTimer countDownTimer;
    int n = 0;
    private TextView mNoiDung;
    private TextView mPhuongAna;
    private TextView mPhuongAnb;
    private TextView mPhuongAnc;
    private TextView mPhuongAnd;
    private TextView mDapAn;
    private TextView textView_Diem;
    private String data;
    private  int socau=0;
    int tongsocau;
    private int diem=0;
    private int tongsodiem;
    private TextView textView_socau;
    private String[] ListButton = new  String[4];
    private int[] Listid = new int[4];
    private String dapAnDung;
    Button btnlinhvuc1;
    Button btnlinhvuc2;
    Button btnlinhvuc3;
    Button btnlinhvuc4;

    Button btndapana;
    Button btndapanb;
    Button btndapanc;
    Button btndapand;
    Button btnDapAn;

    Button btntieptheo;
    Button btn5050;
    View incLinhvuc, incCauhoi;
    private TextView textView_dapan;
    private TextView textView_soCredit;
    private TextView txt_tenuser;
    private int id_linhvuc;

    final int LINH_VUC_LOADER = 0;
    final int CAU_HOI_LOADER = 1;
    TextView DemNguoc;
    private int dapandung = 0;

    private int dem_trai_tim = 0;

    private String phuongana;
    private String phuonganb;
    private String phuonganc;
    private String phuongand;
    private String kytu;
    private String dapan;
    private String kqa,kqb,kqc,kqd;
    private int ada, bda, cda,dda;
    private Dialog dialog_goidien;
    Random rd = new Random();
    int dem = 0;

    private ImageView imvTraiTim1;
    private ImageView imvTraiTim2;
    private ImageView imvTraiTim3;
    private ImageView imvTraiTim4;
    private ImageView imvTraiTim5;
    private ImageView[] listTraiTim = new ImageView[5];
    private int id_imView_Traitim = 0;

    private ImageButton btn_khangia;

    private  Button[] listbutton = new Button[4];
    private int id_dapan_dung;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tro_choi);
        incLinhvuc = findViewById(R.id.incLinhVuc);
        incCauhoi = findViewById(R.id.incCauHoi);
        btnlinhvuc1 = findViewById(R.id.btn_LinhVuc1);
        btnlinhvuc2 = findViewById(R.id.btn_Linhvuc2);
        btnlinhvuc3 = findViewById(R.id.btn_Linhvuc3);
        btnlinhvuc4 = findViewById(R.id.btn_Linhvuc4);

        btntieptheo = findViewById(R.id.button_next);
        btn5050 = findViewById(R.id.button_50_50);
        btnGoiDien = findViewById(R.id.imageButton_dialer);
        btn_khangia = findViewById(R.id.imageButton_ask_audience);

        imvTraiTim1 = findViewById(R.id.imageView_heart1);
        imvTraiTim2 = findViewById(R.id.imageView_heart2);
        imvTraiTim3 = findViewById(R.id.imageView_heart3);
        imvTraiTim4 = findViewById(R.id.imageView_heart4);
        imvTraiTim5 = findViewById(R.id.imageView_heart5);
        listTraiTim[0]=imvTraiTim1;
        listTraiTim[1]=imvTraiTim2;
        listTraiTim[2]=imvTraiTim3;
        listTraiTim[3]=imvTraiTim4;
        listTraiTim[4]=imvTraiTim5;

        FiftyFifty();
        GoiDienChoNguoiThan();
        DemNguoc();

        textView_socau = findViewById(R.id.textView_number);
        textView_Diem=findViewById(R.id.textView_Diem);
        textView_dapan = findViewById(R.id.textView_dap_an);
        textView_soCredit = findViewById(R.id.textView_credit2);
        Intent intent = getIntent();
        textView_soCredit.setText(intent.getStringExtra("luucredit"));
        txt_tenuser = findViewById(R.id.textView_chance);
        String tenuser = intent.getStringExtra("tenuser");
        txt_tenuser.setText(tenuser);
        if(getSupportLoaderManager().getLoader(LINH_VUC_LOADER)!=null){
            getSupportLoaderManager().initLoader(LINH_VUC_LOADER,null,this);
        }
        getSupportLoaderManager().restartLoader(LINH_VUC_LOADER,null,this);

        //cau hoi
        mNoiDung=findViewById(R.id.textView_noi_dung_cau_hoi);
        mPhuongAnd=findViewById(R.id.button_dap_an_d);
        mPhuongAnb=findViewById(R.id.button_dap_an_b);
        mPhuongAnc=findViewById(R.id.button_dap_an_c);
        mPhuongAna=findViewById(R.id.button_dap_an_a);



        btnCallActvityMain =  findViewById(R.id.imageButton_home);
        btnCallActvityMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(TroChoiActivity.this);
                dialog.setContentView(R.layout.dialog_bo_cuoc);
                dialog.setCancelable(false);
                Button btnBoCuoc = dialog.findViewById(R.id.btn_dung);
                Button btnTiepTuc = dialog.findViewById(R.id.btn_tiep_tuc);

                btnBoCuoc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TroChoiActivity.this,PlayerActivity.class);
                        intent.putExtra("credit",textView_soCredit.getText());
                        startActivity(intent);
                        countDownTimer.cancel();
                    }
                });

                btnTiepTuc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        btn_khangia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog_totuvan= new Dialog(TroChoiActivity.this);
                dialog_totuvan.setContentView(R.layout.bieu_do_layout);
                dialog_totuvan.setCanceledOnTouchOutside(false);//->Click vào bên ngoài thì đóng dialog
                dialog_totuvan.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
                toTuVan(dialog_totuvan);
                dialog_totuvan.show();
                btn_khangia.setClickable(false);
                btn_khangia.setBackgroundColor(Color.GRAY);
                Button btn_quaylai = findViewById(R.id.btn_quay_lai);
            }
        });


        btndapana = findViewById(R.id.button_dap_an_a);
        btndapanb = findViewById(R.id.button_dap_an_b);
        btndapanc = findViewById(R.id.button_dap_an_c);
        btndapand = findViewById(R.id.button_dap_an_d);

        listbutton[0]=btndapana;
        listbutton[1]=btndapanb;
        listbutton[2]=btndapanc;
        listbutton[3]=btndapand;

        btndapana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Click_traloi(btndapana,phuongana,mPhuongAna);
            }
        });
        btndapanb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Click_traloi(btndapanb,phuonganb,mPhuongAnb);
            }
        });
        btndapanc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Click_traloi(btndapanc,phuonganc,mPhuongAnc);
            }
        });
        btndapand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Click_traloi(btndapand,phuongand,mPhuongAnd);
            }
        });
    }

    public void DemNguoc()
    {
        DemNguoc = findViewById(R.id.textView_count_down);
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                n++;
                DemNguoc.setText(30 - n + "s");
            }

            @Override
            public void onFinish() {
                Toast.makeText(TroChoiActivity.this, "Hết giờ chọn câu tiếp theo", Toast.LENGTH_SHORT).show();
                btntieptheo.callOnClick();
            }
        };
        countDownTimer.start();
    }
    public void mocauhoi(View view) {
        incCauhoi.setVisibility(View.VISIBLE);
        incLinhvuc.setVisibility(View.INVISIBLE);
        id_linhvuc = Listid[0];
        tongsocau = socau +1;
        textView_socau.setText(tongsocau+"");
        socau = tongsocau;
        if(getSupportLoaderManager().getLoader(CAU_HOI_LOADER)!=null){
            getSupportLoaderManager().initLoader(CAU_HOI_LOADER,null,this);
        }
        getSupportLoaderManager().restartLoader(CAU_HOI_LOADER,null,this);
    }
    public void mocauhoi4(View view) {
        incCauhoi.setVisibility(View.VISIBLE);
        incLinhvuc.setVisibility(View.INVISIBLE);
        id_linhvuc = Listid[3];
        tongsocau = socau +1;
        textView_socau.setText(tongsocau+"");
        socau = tongsocau;
        if(getSupportLoaderManager().getLoader(CAU_HOI_LOADER)!=null){
            getSupportLoaderManager().initLoader(CAU_HOI_LOADER,null,this);
        }
        getSupportLoaderManager().restartLoader(CAU_HOI_LOADER,null,this);
    }

    public void mocauhoi3(View view) {
        incCauhoi.setVisibility(View.VISIBLE);
        incLinhvuc.setVisibility(View.INVISIBLE);
        id_linhvuc = Listid[2];
        tongsocau = socau +1;
        textView_socau.setText(tongsocau+"");
        socau = tongsocau;
        if(getSupportLoaderManager().getLoader(CAU_HOI_LOADER)!=null){
            getSupportLoaderManager().initLoader(CAU_HOI_LOADER,null,this);
        }
        getSupportLoaderManager().restartLoader(CAU_HOI_LOADER,null,this);
    }

    public void mocauhoi2(View view) {
        incCauhoi.setVisibility(View.VISIBLE);
        incLinhvuc.setVisibility(View.INVISIBLE);
        id_linhvuc = Listid[1];
        tongsocau = socau +1;
        textView_socau.setText(tongsocau+"");
        socau = tongsocau;
        if(getSupportLoaderManager().getLoader(CAU_HOI_LOADER)!=null){
            getSupportLoaderManager().initLoader(CAU_HOI_LOADER,null,this);
        }
        getSupportLoaderManager().restartLoader(CAU_HOI_LOADER,null,this);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        if(id == LINH_VUC_LOADER){
            return new LinhVucLoader(this);
        }
        return new CauHoiLoader(this, id_linhvuc);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        int id_loader = loader.getId();
        if(id_loader == LINH_VUC_LOADER){
            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray itemsArray = jsonObject.getJSONArray("data");
                for(int i=0; i<itemsArray.length(); i++) {
                    ListButton[i]=(itemsArray.getJSONObject(i).getString("ten_linh_vuc"));
                    Listid[i] = itemsArray.getJSONObject(i).getInt("id");

                }
                String linhvuc1 = ListButton[0];
                String linhvuc2 = ListButton[1];
                String linhvuc3 = ListButton[2];
                String linhvuc4 = ListButton[3];
                btnlinhvuc1.setText(linhvuc1);
                btnlinhvuc2.setText(linhvuc2);
                btnlinhvuc3.setText(linhvuc3);
                btnlinhvuc4.setText(linhvuc4);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(id_loader == CAU_HOI_LOADER){
            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray itemsArray = jsonObject.getJSONArray("data");
                for(int i=0; i<itemsArray.length(); i++) {
                    String noiDung = itemsArray.getJSONObject(i).getString("noi_dung");
                    mNoiDung.setText(noiDung);
                    String phuongAnA = itemsArray.getJSONObject(i).getString("phuong_an_a");
                    kqa=phuongAnA;
                    kqa=kqa.substring(0,1);
                    mPhuongAna.setText(phuongAnA);
                    String phuongAnB = itemsArray.getJSONObject(i).getString("phuong_an_b");
                    kqb=phuongAnB;
                    kqb=kqb.substring(0,1);
                    mPhuongAnb.setText(phuongAnB);
                    String phuongAnC = itemsArray.getJSONObject(i).getString("phuong_an_c");
                    kqc=phuongAnC;
                    kqc=kqc.substring(0,1);
                    mPhuongAnc.setText(phuongAnC);
                    String phuongAnD = itemsArray.getJSONObject(i).getString("phuong_an_d");
                    kqd=phuongAnD;
                    kqd=kqd.substring(0,1);
                    mPhuongAnd.setText(phuongAnD);
                    String dapAn = itemsArray.getJSONObject(i).getString("dap_an");
                    dapAnDung = dapAn;
                    if(kqa.equals(dapAnDung))
                    {
                        id_dapan_dung=0;
                        Random random = new Random();
                        ada = random.nextInt(100);
                        bda = random.nextInt(100-ada);
                        cda = random.nextInt(100-ada-bda);
                        dda = 100-ada-bda-cda;
                    }
                    if(kqb.equals(dapAnDung))
                    {
                        id_dapan_dung=1;
                        Random random = new Random();
                        bda =  random.nextInt(100);
                        ada = random.nextInt(100-bda);
                        cda = random.nextInt(100-bda-ada);
                        dda = 100-ada-bda-cda;
                    }
                    if(kqc.equals(dapAnDung))
                    {
                        id_dapan_dung=2;
                        Random random = new Random();
                        cda = random.nextInt(100);
                        bda = random.nextInt(100-cda);
                        ada = random.nextInt(100-cda-bda);
                        dda = 100-ada-bda-cda;
                    }
                    if (kqd.equals(dapAnDung))
                    {
                        id_dapan_dung=3;
                        Random random = new Random();
                        dda =  random.nextInt(100);
                        bda = random.nextInt(100-dda);
                        cda = random.nextInt(100-dda-bda);
                        ada = 100-dda-bda-cda;
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
    public void tieptheo(View view) {
        incCauhoi.setVisibility(View.INVISIBLE);
        incLinhvuc.setVisibility(View.VISIBLE);
        int mColor = ContextCompat.getColor(this,R.color.mau_cauhoi);
        btndapana.setBackgroundColor(mColor);
        btndapanb.setBackgroundColor(mColor);
        btndapanc.setBackgroundColor(mColor);
        btndapand.setBackgroundColor(mColor);
        btndapana.setClickable(true);
        btndapanb.setClickable(true);
        btndapanc.setClickable(true);
        btndapand.setClickable(true);
    }

    private void FiftyFifty()
    {
        btn5050.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn5050.setClickable(false);
                btn5050.setBackgroundColor(Color.GRAY);
                if(dapAnDung.equals("A"))
                {
                    dapandung = 0;
                }
                if(dapAnDung.equals("B"))
                {
                    dapandung = 1;
                }
                if(dapAnDung.equals("C"))
                {
                    dapandung = 2;
                }
                if(dapAnDung.equals("D"))
                {
                    dapandung = 3;
                }
                int dem =0;
                for (int i =0; i <4;i++){
                    if(dapandung!=i){
                        listbutton[i].setText("");
                        listbutton[i].setClickable(false);
                        dem++;
                    }
                    if(dem==2){
                        return;
                    }
                }

            }
        });
    }

    public void GoiDienChoNguoiThan()
    {
        final ImageButton btnGoiDien;
        btnGoiDien = findViewById(R.id.imageButton_dialer);
        btnGoiDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(TroChoiActivity.this);
                dialog.setContentView(R.layout.dialog_goi_dien);
                dialog.setCancelable(false);
                TextView txtvDapAn = dialog.findViewById(R.id.textView_dap_an);
                txtvDapAn.setText(dapAnDung);
                dialog.show();
                Button btnThoat = dialog.findViewById(R.id.btn_tro_ve);
                btnThoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnGoiDien.setClickable(false);
                btnGoiDien.setBackgroundColor(Color.GRAY);
            }
        });
    }
    public void xoaTraiTim(int id){
        listTraiTim[id].setImageResource(R.drawable.button_border);
    }

    public void toTuVan(final Dialog dialog){
        {
            BarChart barChart =dialog.findViewById(R.id.barChart);

            ArrayList<BarEntry> datas = new ArrayList<>();
            datas.add(new BarEntry(0,ada));
            datas.add(new BarEntry(1, bda));
            datas.add(new BarEntry(2, cda));
            datas.add(new BarEntry(3, dda));

            BarDataSet barDataSet = new BarDataSet(datas, "");
            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            barDataSet.setValueTextSize(20f);

            BarData barData = new BarData( barDataSet);
            XAxis xAxis = barChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            final String[] labels=new String[]{"A","B","C","D"};
            IndexAxisValueFormatter formatter=new IndexAxisValueFormatter(labels);
            xAxis.setTextSize(20f);
            xAxis.setGranularity(1f);

            xAxis.setValueFormatter(formatter);
            //gán dữ liệu vào barChart
            barChart.setData(barData);

            //Khong ve luoi tren bieu do
            xAxis.setDrawGridLines(false);
            barChart.getAxisLeft().setEnabled(false);
            barChart.getAxisRight().setEnabled(false);
            barChart.getLegend().setEnabled(false);
            barChart.getDescription().setEnabled(false);
            //Zoom bieu do
            barChart.setDoubleTapToZoomEnabled(false);
            //Touch biêu đồ
            barChart.setTouchEnabled(false);

            barChart.animateY(3000);

            barChart.invalidate();

        }
    }
    public int random_trongkhoang(int minN, int maxN){
        Random random = new Random();
        return minN + random.nextInt() % (maxN + 1 - minN);
    }

    public void muaDapAn(View view) {
        Button button_muaDapAn = findViewById(R.id.button_mua_cau_hoi);
        int soCredit = Integer.parseInt(textView_soCredit.getText().toString());
        if (soCredit > 0) {
            int mColor = ContextCompat.getColor(this,R.color.mau_tro_giup);
            button_muaDapAn.setBackgroundColor(mColor);
            listbutton[id_dapan_dung].callOnClick();
            soCredit = soCredit-100;
            String chuoi = soCredit + "";
            textView_soCredit.setText(chuoi);
        }
        else
        {
            Toast.makeText(TroChoiActivity.this, "Số dư Credit của bạn không đủ.", Toast.LENGTH_SHORT).show();
            button_muaDapAn.setClickable(false);
            button_muaDapAn.setBackgroundColor(Color.GRAY);
        }

    }
    public void Click_traloi(Button btnPhuongAn, String phuongan, TextView mTextView){
        phuongan = mTextView.getText().toString();
        kytu = phuongan.substring(0,1);
        dapan = dapAnDung;
        if(Integer.parseInt(textView_socau.getText().toString())>1 && Integer.parseInt(textView_socau.getText().toString())<5){
            int diem_cua_cau=20;
            tongsodiem=diem_cua_cau+diem;
        }
        else if (Integer.parseInt(textView_socau.getText().toString())>4){
            int diem_cua_cau=30;
            tongsodiem=diem_cua_cau+diem;
        }
        else
        {
            tongsodiem=diem+10;
        }
        if(kytu.equals(dapan)){
            btnPhuongAn.setBackgroundColor(Color.GREEN);
            textView_Diem.setText("Điểm: "+tongsodiem);
            diem = tongsodiem;
        }
        else
        {
            btnPhuongAn.setBackgroundColor(Color.YELLOW);
            if(id_imView_Traitim == 5){
                Intent intent = new Intent(TroChoiActivity.this,PlayerActivity.class);
                Toast.makeText(TroChoiActivity.this, "Tro choi ket thuc!!", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            else
            {
                xoaTraiTim(id_imView_Traitim);
                id_imView_Traitim++;
                Toast.makeText(TroChoiActivity.this, "Sai rồi", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
