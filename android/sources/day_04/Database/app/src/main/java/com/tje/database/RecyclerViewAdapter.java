package com.tje.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private String[] notes = {"노트1", "노트2", "노트3", "노트4", "노트5", "노트6", "노트7", "노트8", "노트9", "노트10"};
    private String[] dates = {"17.01.01", "17.02.01", "17.03.01", "17.04.01", "17.05.01", "17.06.01", "17.07.01", "17.08.01", "17.09.01", "17.10.01"};

    public interface RecyclerViewAdapterEventListener {
        void onClick(View view);
    }

    private MemoListDbHelper memoListDbHelper;
    private ArrayList<Memo> memoArrayList;
    private RecyclerViewAdapterEventListener listener;


    // 어떤 액티비티에서 할건지를 정해주는 과정
    public RecyclerViewAdapter (Context context) {
        memoListDbHelper = new MemoListDbHelper(context);
        memoArrayList = memoListDbHelper.loadMemoList();
        Toast.makeText(context, "갯수 : " + memoArrayList.size(), Toast.LENGTH_SHORT).show();
    }

    /*
    public RecyclerViewAdapter (Context context, RecyclerViewAdapterEventListener listener) {
        memoListDbHelper = new MemoListDbHelper(context);
        this.listener = listener;
    }
    */



    public class ViewHolder extends RecyclerView.ViewHolder {// 새로만든 뷰 홀더
        public TextView title;
        public TextView date;
        public ViewHolder(View view) {
            super(view);
            this.title = view.findViewById(R.id.title);
            this.date = view.findViewById(R.id.date);
        }
        /*
        public TextView textView'
        public ViewHolder(TextView textView) {
            super(textView);
            this.textView = textView;
        }
         */
    }

    // 뷰홀더를 생성
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        ViewHolder vh = new ViewHolder(view);
        /*
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = String.format("%s 가 클릭되었습니다.", ((TextView)view.findViewById(R.id.title)).getText().toString());
                Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
        */

        return vh;
        /*
        TextView textView = new TextView(parent.getContext()); // 새로운 텍스트뷰 생성
        ViewHolder vh = new ViewHolder(textView); // ViewHolder에 참조저장
        return vh;
        */
    }

    // 스크롤하여 비워진 뷰홀더의 인덱스 포지션에 데이터를 채워주는 역할
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.title.setText(notes[position]); // 전달받은 ViewHolder 객체의 텍스트뷰에 값 설정
        holder.date.setText(dates[position]);

        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String msg = String.format("%s (%s) 가 클릭되었습니다.", notes[position], dates[position]);
                // String msg = String.format("%s (%s) 가 클릭되었습니다.", holder.title.getText().toString(), holder.date.getText().toString());

                String title_clicked = holder.title.getText().toString();
                Toast.makeText(view.getContext(), title_clicked+"이 클릭 되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });
        */

        Memo memo = memoArrayList.get(position);
        holder.title.setText(memo.getFileName());
        holder.date.setText(memo.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "클릭 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        // return 5;
        // return notes.length;

        if(memoArrayList == null) {
            return 0;
        }
        return memoArrayList.size();
    }
}
