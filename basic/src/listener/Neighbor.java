package listener;

class Neighbor {
	
	BedListener listener;


	
	/*
	 * ��������ν�Ŀ��Ʒ�ת   ��ʵ�����������������һ��������Ľӿڵ�set������
	 * ���ڱ���ķ����е��øýӿ��еķ�����
	 * �ڵ��ø���ʱ���Ϳ��Զ�̬�Ľ��ӿڵ�ʵ����д��ȥ��
	 * �Ӷ��ڵ��ø����еķ���ʱ��������Ʒ�����ʵ�ֵľ�������
	 */
	public void setListener(BedListener listener) {
		this.listener = listener;
	}
	
	
	// ���ݵ��ط��ɷ��棬���������޷���ʾ
	void doInterestingStuff () {
	    
	    // ���¼����͸�������
	    listener.onBedSound("Bedsound_1");
	  }
	
}
