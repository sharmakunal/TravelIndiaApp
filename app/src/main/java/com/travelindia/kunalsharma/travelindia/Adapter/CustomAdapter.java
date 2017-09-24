package com.travelindia.kunalsharma.travelindia.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.travelindia.kunalsharma.travelindia.PojoClasses.State;
import com.travelindia.kunalsharma.travelindia.R;
import java.util.List;

public class CustomAdapter extends BaseAdapter{


    private static final String TAG = "StateViewAdapater";
    List<State> mphotosList;
    private Context Context;

    private static LayoutInflater inflater=null;
    public CustomAdapter(Context context, List<State> photosList) {
        // TODO Auto-generated constructor stub

        this.Context=context;
        this.mphotosList=photosList;
        this.inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mphotosList.size();
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public State getItem(int position) {
        // TODO Auto-generated method stub
        return mphotosList.get(position);
    }

    public void loadNewData(List<State> newPhotos) {
        mphotosList = newPhotos;
        this.notifyDataSetChanged();
    }

    public class Holder
    {
        TextView thubnail_title;
        ImageView thumbnail_images;
        int pos;

        public Holder(View convertView,  int pos) {
            thubnail_title =(TextView) convertView.findViewById(R.id.thubnail_title);
            thumbnail_images =(ImageView) convertView.findViewById(R.id.thumbnail_images);
            this.pos=pos;

        }

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Holder holder;

        if(convertView==null){
            convertView=LayoutInflater.from(Context).inflate(R.layout.gridlayout,parent,false);
            holder= new Holder(convertView,position);
            convertView.setTag(holder);
        }else{
            holder=(Holder)convertView.getTag();
        }
        final State photoItem = mphotosList.get(position);
        holder.thubnail_title.setText(photoItem.getStateName());
        Picasso.with(Context).load(photoItem.getStatethumbnail())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.thumbnail_images);

        return convertView;
    }

}