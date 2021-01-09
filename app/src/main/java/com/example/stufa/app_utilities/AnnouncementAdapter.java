package com.example.stufa.app_utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stufa.R;
import com.example.stufa.activities.AnnouncementBrowsing;
import com.example.stufa.data_models.Announcement;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.ViewHolder>
    implements Filterable
{
    public List<Announcement> announcements;
    private int index;
    private List<Announcement> filterList;
    private FilterHelper filterHelper;

    //Just testing
    private final Context c;

    @Override
    public Filter getFilter() {
        if(filterHelper==null){
            filterHelper = FilterHelper.newInstance(filterList,this);
        }
        return filterHelper;
    }

    interface ItemClickListener {
        void onItemClick(int pos);
    }

    public AnnouncementAdapter(Context context,List<Announcement> list)
    {
        this.c = context;
        announcements = list;
        this.filterList = announcements;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener
    {
        public TextView itemTvTitle;
        public TextView itemTvName;
        public ImageView itemIvView;
        public TextView itemTvDate;
        private ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemTvTitle = (TextView)itemView.findViewById(R.id.tvTitle);
            itemTvName = (TextView)itemView.findViewById(R.id.tvName);
            itemIvView = (ImageView)itemView.findViewById(R.id.ivViewed);
            itemTvDate = (TextView)itemView.findViewById(R.id.tvDate);
        }

        @Override
        public void onClick(View view) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
            index = announcements.indexOf(view.getTag());
            if (announcements.get(index).isViewed() == false)
            {
                announcements.get(index).setViewed(true);
                itemIvView.setImageResource(R.drawable.viewed);
            }
        }

        void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }

    @NonNull
    @Override
    public AnnouncementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.announcement_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementAdapter.ViewHolder holder, int i) {
        //get the current announcement
        Announcement s = announcements.get(i);
        holder.itemTvTitle.setText(announcements.get(i).getTitle());
        holder.itemTvName.setText(announcements.get(i).getStaff().getFirstName()
        + " " + announcements.get(i).getStaff().getLastName());
        if (announcements.get(i).isViewed() == true)
        {
            holder.itemIvView.setImageResource(R.drawable.viewed);
        }
        else
        {
            holder.itemIvView.setImageResource(R.drawable.not_viewed);
        }
        holder.itemTvDate.setText(announcements.get(i).getDate() + "");
        holder.setItemClickListener(pos -> Utilities.sendAnnouncementToActivity(c, s,
                AnnouncementBrowsing.class));
    }

    @Override
    public int getItemCount() {
        return announcements.size();
    }
}
