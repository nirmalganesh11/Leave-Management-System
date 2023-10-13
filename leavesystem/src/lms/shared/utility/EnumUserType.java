package lms.shared.utility;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StringType;

import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class EnumUserType implements UserType {
	
    private static final int[] SQL_TYPES = {Types.VARCHAR};

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public Class returnedClass() {
        return LeaveStatus.class;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x == y || (x != null && y != null && x.equals(y));
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws SQLException {
        String value = (String) StringType.INSTANCE.nullSafeGet(rs, names, session, owner);
        return LeaveStatus.fromValue(value);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws SQLException {
        if (value == null) {
            StringType.INSTANCE.nullSafeSet(st, null, index, session);
        } else {
            StringType.INSTANCE.nullSafeSet(st, ((LeaveStatus) value).getValue(), index, session);
        }
    }

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		
		return null;
	}

	@Override
	public boolean isMutable() {
		
		return false;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		
		return null;
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		
		return null;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		
		return null;
	}

   
}
