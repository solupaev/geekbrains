package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.ItemDto;
import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.cellview.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
import java.util.List;

public class ItemsTableWidget extends Composite {
    @UiField
    CellTable<ItemDto> table;

    private ItemsClient client;

    @UiTemplate("ItemsTable.ui.xml")
    interface ItemsTableBinder extends UiBinder<Widget, ItemsTableWidget> {
    }

    private static ItemsTableBinder uiBinder = GWT.create(ItemsTableBinder.class);

    public ItemsTableWidget() {
        initWidget(uiBinder.createAndBindUi(this));

        TextColumn<ItemDto> idColumn = new TextColumn<ItemDto>() {
            @Override
            public String getValue(ItemDto itemDto) {
                return itemDto.getId().toString();
            }
        };
        table.addColumn(idColumn, "ID");

        TextColumn<ItemDto> titleColumn = new TextColumn<ItemDto>() {
            @Override
            public String getValue(ItemDto itemDto) {
                return itemDto.getTitle();
            }
        };
        table.addColumn(titleColumn, "Title");

        client = GWT.create(ItemsClient.class);

        Column<ItemDto, ItemDto> actionColumn = new Column<ItemDto, ItemDto>(
                new ActionCell<ItemDto>("REMOVE", new ActionCell.Delegate<ItemDto>() {
                    @Override
                    public void execute(ItemDto item) {
                        client.removeItem(item.getId().toString(), new MethodCallback<Void>() {
                            @Override
                            public void onFailure(Method method, Throwable throwable) {
                                GWT.log(throwable.toString());
                                GWT.log(throwable.getMessage());
                            }

                            @Override
                            public void onSuccess(Method method, Void result) {
                                refresh();
                            }
                        });
                    }
                })) {
            @Override
            public ItemDto getValue(ItemDto item) {
                return item;
            }
        };

        table.addColumn(actionColumn, "Actions");

        table.setColumnWidth(idColumn, 100, Style.Unit.PX);
        table.setColumnWidth(titleColumn, 400, Style.Unit.PX);
        table.setColumnWidth(actionColumn, 200, Style.Unit.PX);

        refresh();
    }

    public void refresh() {
        client.getAllItems(new MethodCallback<List<ItemDto>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
                GWT.log(throwable.toString());
                GWT.log(throwable.getMessage());
                Window.alert("Невозможно получить список items: Сервер не отвечает");
            }

            @Override
            public void onSuccess(Method method, List<ItemDto> i) {
                GWT.log("Received " + i.size() + " items");
                List<ItemDto> items = new ArrayList<>();
                items.addAll(i);
                table.setRowData( items);
            }
        });
    }
}
