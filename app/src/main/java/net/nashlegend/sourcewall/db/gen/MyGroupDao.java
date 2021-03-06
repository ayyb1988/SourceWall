package net.nashlegend.sourcewall.db.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table MY_GROUP.
 */
public class MyGroupDao extends AbstractDao<MyGroup, Long> {

    public static final String TABLENAME = "MY_GROUP";

    /**
     * Properties of entity MyGroup.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Section = new Property(1, int.class, "section", false, "SECTION");
        public final static Property Type = new Property(2, int.class, "type", false, "TYPE");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
        public final static Property Value = new Property(4, String.class, "value", false, "VALUE");
        public final static Property Selected = new Property(5, boolean.class, "selected", false, "SELECTED");
        public final static Property Order = new Property(6, int.class, "order", false, "ORDER");
    }

    ;


    public MyGroupDao(DaoConfig config) {
        super(config);
    }

    public MyGroupDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "'MY_GROUP' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'SECTION' INTEGER NOT NULL ," + // 1: section
                "'TYPE' INTEGER NOT NULL ," + // 2: type
                "'NAME' TEXT NOT NULL ," + // 3: name
                "'VALUE' TEXT NOT NULL ," + // 4: value
                "'SELECTED' INTEGER NOT NULL ," + // 5: selected
                "'ORDER' INTEGER NOT NULL );"); // 6: order
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'MY_GROUP'";
        db.execSQL(sql);
    }

    /**
     * @inheritdoc
     */
    @Override
    protected void bindValues(SQLiteStatement stmt, MyGroup entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getSection());
        stmt.bindLong(3, entity.getType());
        stmt.bindString(4, entity.getName());
        stmt.bindString(5, entity.getValue());
        stmt.bindLong(6, entity.getSelected() ? 1l : 0l);
        stmt.bindLong(7, entity.getOrder());
    }

    /**
     * @inheritdoc
     */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /**
     * @inheritdoc
     */
    @Override
    public MyGroup readEntity(Cursor cursor, int offset) {
        MyGroup entity = new MyGroup( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getInt(offset + 1), // section
                cursor.getInt(offset + 2), // type
                cursor.getString(offset + 3), // name
                cursor.getString(offset + 4), // value
                cursor.getShort(offset + 5) != 0, // selected
                cursor.getInt(offset + 6) // order
        );
        return entity;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void readEntity(Cursor cursor, MyGroup entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSection(cursor.getInt(offset + 1));
        entity.setType(cursor.getInt(offset + 2));
        entity.setName(cursor.getString(offset + 3));
        entity.setValue(cursor.getString(offset + 4));
        entity.setSelected(cursor.getShort(offset + 5) != 0);
        entity.setOrder(cursor.getInt(offset + 6));
    }

    /**
     * @inheritdoc
     */
    @Override
    protected Long updateKeyAfterInsert(MyGroup entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    /**
     * @inheritdoc
     */
    @Override
    public Long getKey(MyGroup entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }

}
