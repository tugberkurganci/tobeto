import React, { useState } from 'react';

interface PaymentFormProps {
  onSuccess: () => void;
  onFailure: () => void;
}

const PaymentForm: React.FC<PaymentFormProps> = ({ onSuccess, onFailure }) => {
  const [cardNumber, setCardNumber] = useState('');
  const [expiryDate, setExpiryDate] = useState('');
  const [cvc, setCvc] = useState('');

  const handlePaymentSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    // Bu kısımda, gerçek bir ödeme işlemi için ödeme hizmet sağlayıcısı API'sine istek gönderilebilir.
    // Bu örnekte sadece basit bir kontrol yapılıyor.
    if (cardNumber && expiryDate && cvc) {
      onSuccess();
    } else {
      onFailure();
    }
  };

  return (
    <form onSubmit={handlePaymentSubmit} className="mt-4">
    <div className="mb-3">
      <label className="form-label">Kart Numarası:</label>
      <input
        type="text"
        value={cardNumber}
        onChange={(e) => setCardNumber(e.target.value)}
        className="form-control"
        placeholder="1234 5678 9101 1121"
      />
    </div>
    <div className="mb-3">
      <label className="form-label">Son Kullanma Tarihi:</label>
      <input
        type="text"
        value={expiryDate}
        onChange={(e) => setExpiryDate(e.target.value)}
        className="form-control"
        placeholder="MM/YY"
      />
    </div>
    <div className="mb-3">
      <label className="form-label">CVC:</label>
      <input
        type="text"
        value={cvc}
        onChange={(e) => setCvc(e.target.value)}
        className="form-control"
        placeholder="123"
      />
    </div>
    <button type="submit" className="btn btn-primary">
      Ödemeyi Tamamla
    </button>
  </form>
  );
};

export default PaymentForm;
