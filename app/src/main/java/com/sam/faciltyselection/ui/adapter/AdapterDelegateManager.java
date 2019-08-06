package com.sam.faciltyselection.ui.adapter;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.sam.faciltyselection.ui.adapter.delegate.AdapterDelegate;

public class AdapterDelegateManager<T extends AdapterDelegate, V> {

    private final String TAG = AdapterDelegateManager.class.getSimpleName();

    protected SparseArrayCompat<T> delegates = null;

    public AdapterDelegateManager() {
        delegates = new SparseArrayCompat<>();
    }

    public void addDelegate(int viewType, T delegate) {
        delegates.put(viewType, delegate);
    }

    T getDelegateForData(V data) {
        for (int i = 0; i < delegates.size(); i++) {
            int key = delegates.keyAt(i);
            T obj = delegates.get(key);
            if (obj.isItemforViewType(data)) {
                int viewType = obj.getViewType(data);
                return delegates.get(viewType);
            }
        }
        return null;
    }

    public T getDelegateForViewType(int viewType) {
        return delegates.get(viewType);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterDelegate delegate = delegates.get(viewType);
        RecyclerView.ViewHolder holder = delegate.onCreateViewHolder(parent, viewType);
        return holder;
    }

    public void onBindViewHolder(@NonNull V items, int position, @NonNull RecyclerView.ViewHolder viewHolder) {
        AdapterDelegate delegate = getDelegateForData(items);
        if(delegate == null){
            return;
        }
        delegate.onBindViewHolder(items, viewHolder, position);
    }

    public int getItemViewType(V data) {
        for (int i = 0; i < delegates.size(); i++) {
            int key = delegates.keyAt(i);
            T obj = delegates.get(key);
            if (obj.isItemforViewType(data)) {
                return obj.getViewType(data);
            }
        }
        return 0;
    }

}

