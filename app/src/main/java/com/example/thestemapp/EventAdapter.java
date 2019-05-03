package com.example.thestemapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventHolder>
{
    private List<Event> list;
    private OnItemClickListener listener;

    public interface OnItemClickListener
    {
        void onItemClick(View v, int pos);
    }

    public void setOnItemClickListener(OnItemClickListener listen)
    {
        listener = listen;
    }

    public EventAdapter(List<Event> data)
    {
        list = data;
    }

    public class EventHolder extends RecyclerView.ViewHolder
    {
        public TextView name;

        public EventHolder(View v)
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
    public EventAdapter.EventHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //Event Layout
        View eventView = inflater.inflate(R.layout.item_event, parent, false);

        EventHolder vh = new EventHolder(eventView);
        return vh;
    }

    @Override
    public void onBindViewHolder(EventAdapter.EventHolder viewHolder, int position)
    {
        Event event = list.get(position);

        TextView text = viewHolder.name;
        text.setText(event.getTitle());
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}
