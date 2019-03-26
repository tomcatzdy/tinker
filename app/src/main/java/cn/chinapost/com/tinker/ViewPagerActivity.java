package cn.chinapost.com.tinker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        listView = findViewById(R.id.list_view);
        initListView();
    }

    private void initListView() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add("第"+i+"条");
        }
        ListViewAdapter adapter = new ListViewAdapter(data);
        listView.setAdapter(adapter);



    }


    private class ListViewAdapter extends BaseAdapter{


        private  List<String> data ;
        public ListViewAdapter(List<String> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view==null){
                viewHolder = new ViewHolder();
                view = View.inflate(ViewPagerActivity.this,R.layout.item_list_view,null);
                viewHolder.textView = view.findViewById(R.id.tv_list_view);
                view.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) view.getTag();
            }

            String item = (String) getItem(i);
            viewHolder.textView.setText(item);


            return view;
        }


        class ViewHolder {
           private TextView textView ;

        }
    }
}
