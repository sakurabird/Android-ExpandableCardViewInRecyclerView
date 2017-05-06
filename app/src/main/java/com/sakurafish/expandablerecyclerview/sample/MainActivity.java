package com.sakurafish.expandablerecyclerview.sample;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sakurafish.expandablerecyclerview.sample.databinding.ActivityMainBinding;
import com.sakurafish.expandablerecyclerview.sample.databinding.RecyclerItemBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ObservableList<RecyclerItemViewModel> list = new ObservableArrayList<>();
        for (int i = 0; i < 50; i++) {
            RecyclerItemViewModel viewModel = new RecyclerItemViewModel("text1 : " + i, "text2 : " + i);
            list.add(viewModel);
        }
        binding.recyclerView.setAdapter(new ListAdapter(this, list));
    }

    private static class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

        public ListAdapter(@NonNull Context context, @NonNull ObservableList<RecyclerItemViewModel> list) {
            this.context = context;
            this.list = list;
        }

        private final Context context;
        private final List<RecyclerItemViewModel> list;

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(context, parent);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final RecyclerItemViewModel viewModel = getItem(position);
            if (viewModel.isExpanded()) {
                holder.binding.expandButton.setSelected(false);
                holder.binding.expandableLayout.expand(true);
            } else {
                holder.binding.expandButton.setSelected(true);
                holder.binding.expandableLayout.collapse(true);
            }

            viewModel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewModel.isExpanded()) {
                        holder.binding.expandButton.setSelected(false);
                        holder.binding.expandableLayout.collapse(true);
                    } else {
                        holder.binding.expandButton.setSelected(true);
                        holder.binding.expandableLayout.expand(true);
                    }
                    viewModel.setExpanded(!viewModel.isExpanded());
                }
            });

            viewModel.setExpandButtonText(position + ". Tap to expand");

            holder.binding.setViewModel(viewModel);
            holder.binding.executePendingBindings();
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public RecyclerItemViewModel getItem(int position) {
            return list.get(position);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            RecyclerItemBinding binding;

            public ViewHolder(Context context, ViewGroup parent) {
                super(LayoutInflater.from(context).inflate(R.layout.recycler_item, parent, false));
                binding = DataBindingUtil.bind(itemView);
            }
        }
    }
}
