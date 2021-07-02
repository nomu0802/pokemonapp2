package com.example.pokemonapp2;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.Adapter;
import static androidx.recyclerview.widget.RecyclerView.OnClickListener;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class
ptrecycle extends Adapter<ptrecycle.MainViewHolder>  {
    private List<ptfrag.RowData> rowDataList;
    private  Context context;
    private MainViewHolder holde;
    private  int pos;
    String posid;
    private OnClickListener listener;
    private FragmentActivity myContext;
    OnClickListener m_listener;

    public ptrecycle(Context d, List<ptfrag.RowData> ptRowData, FragmentActivity fragManagerd) {
        this.rowDataList = ptRowData;
        context =d;
        myContext=fragManagerd;
    }




    public void setOnItemClickListener(View.OnClickListener listener){
        m_listener = listener;
    }


    static  class MainViewHolder extends ViewHolder{

        TextView test;
        ConstraintLayout lay;
        ImageView pkimg;
        TextView name;
        TextView level;
        TextView dougu;
 
        public MainViewHolder(@NonNull View itemView) {

            super(itemView);
            lay = itemView.findViewById(R.id.lay);
            test =itemView.findViewById(R.id.test);
            pkimg = itemView.findViewById(R.id.ptimg);
            name = itemView.findViewById(R.id.pkmname);
            level = itemView.findViewById(R.id.ptlevel);
            dougu = itemView.findViewById(R.id.item);
        }

    }

//読み込み
    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pt_main, parent, false);
        // クリックリスナを搭載
        //クリックイベントを登録
        final  MainViewHolder holder = new MainViewHolder(view);




        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                posid= String.valueOf(position+1);
                idg =posid;
                ptfrag.RowData rowData = rowDataList.get(position);
                String posid = rowData.test;
                String ptid = rowData.id;

                //ptダイアグラムを作成
                DialogFragment dialogFragment = new pt(context,idg,name,posid,ptid);
                FragmentManager fragManager = myContext.getSupportFragmentManager();
                dialogFragment.show(fragManager, "my_dialog2");
            }
        });

        return holder;
    }

    void onItemClick(View view, int position, String itemData) {

        //アダプタのインスタンスを作る際
        //このメソッドをオーバーライドして
        //クリックイベントの処理を設定する
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        holde=holder;
        pos = position;
        ptfrag.RowData rowData = this.rowDataList.get(position);


        if(rowData.test.equals("9999")){
            holder.test.setText("");
        }
        else{
            db(rowData.test);

            Bitmap image = getBitmapFromAsset(idg);
            holder.pkimg.setImageBitmap(image);
            holder.name.setText(name);
            holder.level.setText(level);
            holder.dougu.setText(dougu);
            }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    String idg;
    String name;
    String level;
    String dougu;
    public void db(String read){

        //データの参照
        //ptOpenhelperから外部キーで取得
        MyOpenHelper helper2;
        helper2 = new MyOpenHelper(context);
        SQLiteDatabase db = helper2.getReadableDatabase();
        Cursor cursor = db.query(
                "pokemondb",
                new String[]{"ID","name","level","dougu"},
                "_ID = ?",
                new String[]{read},
                null, null, null
        );

        cursor.moveToFirst();
        idg = cursor.getString(0);
        name= cursor.getString(1);
        int levela = Integer.parseInt(cursor.getString(2))+1;
        level = String.valueOf(levela);
        dougu = cursor.getString(3);




        cursor.close();


    }


    private Bitmap getBitmapFromAsset(String strName){
        AssetManager mngr = context.getAssets();
        InputStream is = null;

        try {
            is = mngr.open("sprites/"+strName+".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        return bitmap;
    }

}

