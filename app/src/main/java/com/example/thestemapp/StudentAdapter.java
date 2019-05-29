package com.example.thestemapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder>
{
    private List <String> list;
    private StudentAdapter.OnItemClickListener listener;

    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(StudentAdapter.OnItemClickListener listen)
    {
        listener = listen;
    }

    public StudentAdapter(List<String> data)
    {
        list = data;
    }

    public class StudentHolder extends RecyclerView.ViewHolder
    {
        public TextView name;

        public StudentHolder(View v)
        {
            super(v);

            name = (TextView) v.findViewById(R.id.evtName);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(v, position);
                        }
                    }
                }
            });
        }
    }

    @Override
    public StudentAdapter.StudentHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View eventView = inflater.inflate(R.layout.item_event, parent, false);

        StudentAdapter.StudentHolder vh = new StudentAdapter.StudentHolder(eventView);
        return vh;
    }

    @Override
    public void onBindViewHolder(StudentAdapter.StudentHolder viewHolder, int position)
    {
        TextView text = viewHolder.name;
        text.setText(list.get(position));
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}
