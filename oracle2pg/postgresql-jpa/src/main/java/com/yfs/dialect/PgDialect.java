package com.yfs.dialect;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL81Dialect;
import org.hibernate.type.descriptor.sql.BlobTypeDescriptor;
import org.hibernate.type.descriptor.sql.ClobTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

public class PgDialect extends PostgreSQL81Dialect {

	@Override
	public SqlTypeDescriptor getSqlTypeDescriptorOverride(int sqlCode) {
		SqlTypeDescriptor descriptor;
		switch (sqlCode) {
		case Types.BLOB:
			/*
			 * Force BLOB binding. Otherwise, byte[] fields annotated with @Lob will attempt
			 * to use BlobTypeDescriptor.PRIMITIVE_ARRAY_BINDING. Since the dialect uses oid
			 * for Blobs, byte arrays cannot be used.
			 */
//			descriptor = BlobTypeDescriptor.BLOB_BINDING;
			descriptor = BlobTypeDescriptor.STREAM_BINDING;
			break;
		case Types.CLOB:
//			descriptor = ClobTypeDescriptor.CLOB_BINDING;
			descriptor = ClobTypeDescriptor.STREAM_BINDING;
			break;
		default:
			descriptor = super.getSqlTypeDescriptorOverride(sqlCode);
			break;
		}
		return descriptor;
	}

}
