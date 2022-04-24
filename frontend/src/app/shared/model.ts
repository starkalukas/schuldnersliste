export interface IAccount {
  id: number,
  address: string,
  phone: string,
  email: string,
  name: string
}

export interface IDebtor {
  account: IAccount,
  name: string
}

export interface IEntryCreate {
  date: Date,
  amount: number,
  reason: string,
  edited: boolean,
  debtor: {
    id: number
  }
}

export interface IDebtorDto {
  id: number,
  name: string,
  amount: number
}

export interface IEntryDto {
  id: number,
  amount: number,
  reason: string,
  edited: boolean,
  date: Date
}
