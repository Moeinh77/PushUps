package data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.taan.hasani.moein.pushups.R;
import com.taan.hasani.moein.pushups.pushup_activity_detail;

import java.util.ArrayList;

import model.set;

/**
 * Created by Moein on 7/7/2017.
 */

public class CustomListViewAdapter extends ArrayAdapter<set> {

    private int Layoutresource;
    private Activity activity;
    private ArrayList<set> set_list=new ArrayList<>();

    public CustomListViewAdapter(Activity act,int resource,
                                 ArrayList<set> data)
    {
        super(act,resource,data);
        Layoutresource =resource;
        activity = act;
        set_list = data;
        notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return set_list.size();
    }

    @Override
    public set getItem(int position) {
        return set_list.get(position);
    }

    @Override
    public int getPosition(set item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position,View convertView, ViewGroup parent) {

        View row=convertView;
        Viewholder holder=null;

        if (row == null||(row.getTag()==null)) {
            LayoutInflater inflater= LayoutInflater.from(activity);
            row=inflater.inflate(Layoutresource,null);

            holder=new Viewholder();

            holder.date=(TextView)row.findViewById(R.id.ddd);
            holder.number=(TextView)row.findViewById(R.id.nnn);

            row.setTag(holder);

        }else{
            holder=(Viewholder)row.getTag();
        }

        holder.Set=getItem(position);

        holder.date.setText(holder.Set.getDate());
        holder.number.setText(String.valueOf(holder.Set.getTimes()));

        final Viewholder finalHolder = holder;
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(activity,pushup_activity_detail.class);

                Bundle mBundle=new Bundle();
                mBundle.putSerializable("My object", finalHolder.Set);
                i.putExtras(mBundle);
                activity.startActivity(i);
                activity.finish();

            }
        });
        return row;
    }


    public class Viewholder{
        set Set;
        TextView number;
        TextView date;
    }
}
